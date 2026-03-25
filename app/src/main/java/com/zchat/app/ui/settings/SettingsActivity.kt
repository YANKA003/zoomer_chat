package com.zchat.app.ui.settings

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.ListPreference
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.SwitchPreferenceCompat
import com.zchat.app.R
import com.zchat.app.data.Repository

class SettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.settings_container, SettingsFragment())
            .commit()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        title = "Настройки"
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    class SettingsFragment : PreferenceFragmentCompat() {
        private var repository: Repository? = null

        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            try {
                repository = Repository(requireContext().applicationContext)
                setPreferencesFromResource(R.xml.root_preferences, rootKey)
                setupPreferences()
            } catch (e: Exception) {
                Toast.makeText(requireContext(), "Ошибка загрузки настроек", Toast.LENGTH_SHORT).show()
            }
        }

        private fun setupPreferences() {
            // Account settings
            findPreference<Preference>("account_info")?.setOnPreferenceClickListener {
                Toast.makeText(requireContext(), "Редактирование профиля", Toast.LENGTH_SHORT).show()
                true
            }

            findPreference<Preference>("phone_number")?.setOnPreferenceClickListener {
                Toast.makeText(requireContext(), "Изменение номера телефона", Toast.LENGTH_SHORT).show()
                true
            }

            // Chat settings
            findPreference<ListPreference>("chat_background")?.setOnPreferenceChangeListener { _, newValue ->
                repository?.preferencesManager?.chatBackground = newValue.toString()
                true
            }

            findPreference<SwitchPreferenceCompat>("enable_animations")?.setOnPreferenceChangeListener { _, newValue ->
                repository?.preferencesManager?.enableAnimations = newValue as Boolean
                true
            }

            // Privacy settings
            findPreference<SwitchPreferenceCompat>("show_online_status")?.setOnPreferenceChangeListener { _, newValue ->
                repository?.preferencesManager?.showOnlineStatus = newValue as Boolean
                true
            }

            findPreference<SwitchPreferenceCompat>("app_lock")?.setOnPreferenceChangeListener { _, newValue ->
                repository?.preferencesManager?.appLockEnabled = newValue as Boolean
                true
            }

            // Notifications
            findPreference<ListPreference>("notification_sound")?.setOnPreferenceChangeListener { _, newValue ->
                repository?.preferencesManager?.notificationSound = newValue.toString()
                true
            }

            findPreference<SwitchPreferenceCompat>("announce_caller")?.setOnPreferenceChangeListener { _, newValue ->
                repository?.preferencesManager?.announceCallerName = newValue as Boolean
                true
            }

            // Battery saver
            findPreference<SwitchPreferenceCompat>("battery_saver")?.setOnPreferenceChangeListener { _, newValue ->
                if (newValue as Boolean) {
                    Toast.makeText(requireContext(), "Режим энергосбережения включен", Toast.LENGTH_SHORT).show()
                }
                true
            }

            // Premium
            findPreference<Preference>("premium")?.setOnPreferenceClickListener {
                Toast.makeText(requireContext(), "ZChat Premium - расширенные функции", Toast.LENGTH_LONG).show()
                repository?.preferencesManager?.premiumEnabled = true
                true
            }

            // VPN Detection
            findPreference<Preference>("vpn_detection")?.setOnPreferenceClickListener {
                Toast.makeText(requireContext(), "Обнаружение VPN активно", Toast.LENGTH_SHORT).show()
                true
            }
        }
    }
}

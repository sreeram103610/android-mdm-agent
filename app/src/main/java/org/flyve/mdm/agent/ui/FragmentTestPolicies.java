/*
 * Copyright Teclib. All rights reserved.
 *
 * Flyve MDM is a mobile device management software.
 *
 * Flyve MDM is free software: you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 3
 * of the License, or (at your option) any later version.
 *
 * Flyve MDM is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * ------------------------------------------------------------------------------
 * @author    Rafael Hernandez
 * @copyright Copyright Teclib. All rights reserved.
 * @license   GPLv3 https://www.gnu.org/licenses/gpl-3.0.html
 * @link      https://github.com/flyve-mdm/android-mdm-agent
 * @link      https://flyve-mdm.com
 * ------------------------------------------------------------------------------
 */

package org.flyve.mdm.agent.ui;

import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;

import org.flyve.mdm.agent.R;
import org.flyve.mdm.agent.data.PoliciesData;
import org.flyve.mdm.agent.services.PoliciesConnectivity;
import org.flyve.mdm.agent.services.PoliciesDeviceManager;
import org.flyve.mdm.agent.utils.ConnectionHTTP;
import org.flyve.mdm.agent.utils.FlyveLog;
import org.flyve.mdm.agent.utils.Helpers;
import org.flyve.mdm.agent.utils.StorageFolder;

public class FragmentTestPolicies extends Fragment {

    private PoliciesData cache;
    private PoliciesDeviceManager mdm;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_test_policies, container, false);

        cache = new PoliciesData(FragmentTestPolicies.this.getContext());
        mdm = new PoliciesDeviceManager(FragmentTestPolicies.this.getContext());

        Switch swGPS = v.findViewById(R.id.swGPS);

        swGPS.setChecked(cache.getConnectivityGPSDisable());
        swGPS.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                cache.setConnectivityGPSDisable(isChecked);
                if(isChecked) {
                    PoliciesConnectivity.disableGps(isChecked);
                }
            }
        });

        Switch swAirplane = v.findViewById(R.id.swAirplane);
        swAirplane.setChecked(cache.getConnectivityAirplaneModeDisable());
        swAirplane.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                cache.setConnectivityAirplaneModeDisable(isChecked);
                PoliciesConnectivity.disableAirplaneMode(isChecked);
            }
        });

        Switch swBluetooth = v.findViewById(R.id.swBluetooth);
        swBluetooth.setChecked(cache.getConnectivityBluetoothDisable());
        swBluetooth.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                cache.setConnectivityBluetoothDisable(isChecked);
                if(isChecked) {
                    PoliciesConnectivity.disableBluetooth(isChecked);
                }
            }
        });

        Switch swWifi = v.findViewById(R.id.swWifi);
        swWifi.setChecked(cache.getConnectivityWifiDisable());
        swWifi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                cache.setConnectivityWifiDisable(isChecked);
                if(isChecked) {
                    PoliciesConnectivity.disableWifi(isChecked);
                }
            }
        });

        Switch swNFC = v.findViewById(R.id.swNFC);
        swNFC.setChecked(cache.getConnectivityRoamingDisable());
        swNFC.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                cache.setConnectivityNFCDisable(isChecked);
                if(isChecked) {
                    PoliciesConnectivity.disableNFC(isChecked);
                }
            }
        });

        Switch swHostpot = v.findViewById(R.id.swHostpot);
        swHostpot.setChecked(cache.getConnectivityHostpotTetheringDisable());
        swHostpot.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                cache.setConnectivityHostpotTetheringDisable(isChecked);
                if(isChecked) {
                    PoliciesConnectivity.disableHostpotTethering(isChecked);
                }
            }
        });

        Switch swMobileLine = v.findViewById(R.id.swMobileLine);
        swMobileLine.setChecked(cache.getConnectivityMobileLineDisable());
        swMobileLine.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                cache.setConnectivityMobileLineDisable(isChecked);
                if(isChecked) {
                    PoliciesConnectivity.disableMobileLine(isChecked);
                }
            }
        });

        Switch swUsbOnTheGo = v.findViewById(R.id.swUsbOnTheGo);
        swUsbOnTheGo.setChecked(cache.getConnectivityUsbFileTransferProtocolsDisable());
        swUsbOnTheGo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                cache.setConnectivityUsbFileTransferProtocolsDisable(isChecked);
                if(isChecked) {
                    PoliciesConnectivity.disableAllUsbFileTransferProtocols(isChecked);
                }
            }
        });

        Button btnLock = v.findViewById(R.id.btnLock);
        btnLock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mdm.lockScreen();
            }
        });

        Switch swDisableCamera = v.findViewById(R.id.swDisableCamera);
        swDisableCamera.setChecked(cache.getDisableCamera());
        swDisableCamera.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mdm.disableCamera(isChecked);
            }
        });

        Switch swStorageEncryptionDevice = v.findViewById(R.id.swStorageEncryptionDevice);
        swStorageEncryptionDevice.setChecked(cache.getStorageEncryptionDevice());
        swStorageEncryptionDevice.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mdm.storageEncryptionDevice(isChecked);
            }
        });

        Button btnReboot = v.findViewById(R.id.btnReboot);
        btnReboot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mdm.reboot();
            }
        });

        Button btnClearMQTT = v.findViewById(R.id.btnCleatMQTT);
        btnClearMQTT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Helpers.deleteMQTTCache(FragmentTestPolicies.this.getContext());
                } catch (Exception ex) {
                    FlyveLog.e(ex.getMessage());
                }
            }
        });

        final EditText edtPasswordLength = v.findViewById(R.id.edtPasswordLength);
        final EditText edtPasswordMinimumLetters = v.findViewById(R.id.edtPasswordMinimumLetters);
        final EditText edtPasswordMinimumLowerCase =  v.findViewById(R.id.edtPasswordMinimumLowerCase);
        final EditText edtPasswordMinimumUpperCase =  v.findViewById(R.id.edtPasswordMinimumUpperCase);
        final EditText edtPasswordMinimumNonLetter = v.findViewById(R.id.edtPasswordMinimumNonLetter);
        final EditText edtPasswordMinimumNumeric = v.findViewById(R.id.edtPasswordMinimumNumeric);
        final EditText edtPasswordMinimumSymbols = v.findViewById(R.id.edtPasswordMinimumSymbols);
        final EditText edtMaximumFailedPasswordsForWipe = v.findViewById(R.id.edtMaximumFailedPasswordsForWipe);
        final EditText edtMaximumTimeToLock = v.findViewById(R.id.edtMaximumTimeToLock);

        Button btnPassword = v.findViewById(R.id.btnPassword);
        btnPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mdm.setPasswordLength( Integer.parseInt( edtPasswordLength.getText().toString() ) );
                mdm.setPasswordMinimumLetters( Integer.parseInt( edtPasswordMinimumLetters.getText().toString() ) );
                mdm.setPasswordMinimumUpperCase( Integer.parseInt( edtPasswordMinimumUpperCase.getText().toString() ) );
                mdm.setPasswordMinimumLowerCase( Integer.parseInt( edtPasswordMinimumLowerCase.getText().toString() ) );
                mdm.setPasswordMinimumNonLetter( Integer.parseInt( edtPasswordMinimumNonLetter.getText().toString() ) );
                mdm.setPasswordMinimumNumeric( Integer.parseInt( edtPasswordMinimumNumeric.getText().toString() ) );
                mdm.setPasswordMinimumSymbols( Integer.parseInt( edtPasswordMinimumSymbols.getText().toString() ) );
                mdm.setMaximumFailedPasswordsForWipe( Integer.parseInt( edtMaximumFailedPasswordsForWipe.getText().toString() ) );
                mdm.setMaximumTimeToLock( Integer.parseInt( edtMaximumTimeToLock.getText().toString() ) );
            }
        });

        Button btnPasswordEnable = v.findViewById(R.id.btnPasswordEnable);
        btnPasswordEnable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mdm.enablePassword();
            }
        });

        Button btnDownloadFile = v.findViewById(R.id.btnDownloadFile);
        btnDownloadFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    public void run() {
                        //
                        String path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS).getAbsolutePath() + "/CHANGELOG.md";
                        FlyveLog.d(path);
                        ConnectionHTTP.getSyncFile("https://raw.githubusercontent.com/flyve-mdm/android-mdm-agent/develop/CHANGELOG.md", path);
                    }
                }).start();


            }
        });

        Button btnDownloadAPK = v.findViewById(R.id.btnDownloadAPK);
        btnDownloadAPK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String path = new StorageFolder(getContext()).getDocumentsDir() + "/flyve-apk.apk";
                FlyveLog.d(path);
                ConnectionHTTP.getSyncFile("https://f-droid.org/repo/org.flyve.inventory.agent_37960.apk", path);
            }
        });

        Button btnEnablePassword = v.findViewById(R.id.btnEnablePassword);
        btnEnablePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Helpers.sendToNotificationBar(getContext(), 1009, "MDM Agent", "Please create a new password", true, MainActivity.class, "TestPolicies");
            }
        });

        Button btnInstallSilently = v.findViewById(R.id.btnInstallSilently);
        btnInstallSilently.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String path = new StorageFolder(FragmentTestPolicies.this.getContext()).getDownloadDir() + "/test.apk";
                Helpers.installApkSilently(path);
            }
        });

        return v;
    }
}

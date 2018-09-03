package cn.earhear.hadeviceapi;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cn.earhear.hadevicelib.DeviceInfo;
import cn.earhear.hadevicelib.DeviceState;
import cn.earhear.hadevicelib.HAParameter;
import cn.earhear.hadevicelib.HAParameterList;
import cn.earhear.hadevicelib.constant.ErrorType;
import cn.earhear.hadevicelib.HADeviceManager;
import cn.earhear.hadevicelib.callback.ConnectCallback;
import cn.earhear.hadevicelib.callback.DetectCallback;
import cn.earhear.hadevicelib.callback.ConfigCallback;
import cn.earhear.hadevicelib.constant.FittingAlgorithm;
import cn.earhear.hadevicelib.constant.OperationType;
import cn.earhear.hadevicelib.constant.SideSelect;

/**
 * Created by ice88 on 2018-5-11.
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String TYPE_SYS = "sys";
    private static final String TYPE_LEFT = "left";
    private static final String TYPE_RIGHT = "right";

    private Button btn_scan;
    private EditText et_mem_choice;

    private HADeviceManager deviceManager;
    private DeviceInfoAdapter deviceInfoAdapter;
    private int connPos;

    private TableLayout operation_table_layout;

    private List<AllParameter> allParameterList;
    private ListView allParamListView;
    private ParamInfoAdapter paramInfoAdapter;

    private DetectCallback detectCallback;
    private ConnectCallback connectCallback;
    private ConfigCallback configCallback;

    private DeviceState curState;
    private int currentMemory;
    private List<HAParameterList> sysParameterList;
    private List<HAParameterList> memParameterList;

    private PureToneManager pureToneManager;

    private int[] left_hl;
    private int[] right_hl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        prepareHearingTest();
        deviceManagerInit();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        pureToneManager.restore_tone();
        deviceManager.destroy();
        deviceInfoAdapter.deviceListClear();
        paramInfoAdapter.parameterListClear();
    }

    private void initView() {
        btn_scan = findViewById(R.id.btn_scan);
        btn_scan.setText(getString(R.string.start_scan));
        btn_scan.setOnClickListener(this);

        et_mem_choice = findViewById(R.id.et_mem_choice);

        Button btn_set_mem_choice = findViewById(R.id.btn_set_mem);
        btn_set_mem_choice.setOnClickListener(this);

        Button btn_set_sys_param = findViewById(R.id.btn_set_sys_param);
        btn_set_sys_param.setOnClickListener(this);

        Button btn_set_mem_param = findViewById(R.id.btn_set_mem_param);
        btn_set_mem_param.setOnClickListener(this);

        Spinner spn_pure_tone = findViewById(R.id.spn_pure_tone);
        spn_pure_tone.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Button btn_freq_left = findViewById(R.id.btn_freq_left);
                String left_freq = "L"+parent.getItemAtPosition(position).toString();
                btn_freq_left.setText(left_freq);
                Button btn_freq_right = findViewById(R.id.btn_freq_right);
                String right_freq = "R"+parent.getItemAtPosition(position).toString();
                btn_freq_right.setText(right_freq);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                parent.setSelection(0);
            }
        });

        Button btn_save_hl = findViewById(R.id.btn_save_hl);
        btn_save_hl.setOnClickListener(this);

        Button btn_auto_fitting = findViewById(R.id.btn_auto_fitting);
        btn_auto_fitting.setOnClickListener(this);

        Button btn_read_param = findViewById(R.id.btn_read_param);
        btn_read_param.setOnClickListener(this);

        Button btn_freq_left = findViewById(R.id.btn_freq_left);
        btn_freq_left.setOnClickListener(this);

        Button btn_freq_right = findViewById(R.id.btn_freq_right);
        btn_freq_right.setOnClickListener(this);

        operation_table_layout = findViewById(R.id.layout_operation);
        operation_table_layout.setVisibility(View.GONE);

        paramInfoAdapter = new ParamInfoAdapter(this);
        paramInfoAdapter.setOnValueChangeListener(new ParamInfoAdapter.OnValueChangeListener() {
            @Override
            public void onValueChanging(int pos, int value) {

            }

            @Override
            public void onValueChanged(int pos, int value) {
                AllParameter parameter = paramInfoAdapter.getItem(pos);
                switch (parameter.getType()) {
                    case TYPE_SYS:
                        sysParameterList.get(0).getHAParameterById(parameter.getParameter().getId()).setValue(value);
                        break;
                    case TYPE_LEFT:
                        memParameterList.get(SideSelect.S_UNILATERAL_LEFT).getHAParameterById(parameter.getParameter().getId()).setValue(value);
                        break;
                    case TYPE_RIGHT:
                        memParameterList.get(SideSelect.S_UNILATERAL_RIGHT).getHAParameterById(parameter.getParameter().getId()).setValue(value);
                        break;
                    default:
                        break;
                }
            }
        });
        paramInfoAdapter.setOnBooleanValueChangeListener(new ParamInfoAdapter.OnBooleanValueChangeListener() {
            @Override
            public void onBooleanChanged(int pos, boolean isValue) {
                AllParameter parameter = paramInfoAdapter.getItem(pos);
                switch (parameter.getType()) {
                    case TYPE_SYS:
                        sysParameterList.get(0).getHAParameterById(parameter.getParameter().getId()).setValue(isValue);
                        break;
                    case TYPE_LEFT:
                        memParameterList.get(SideSelect.S_UNILATERAL_LEFT).getHAParameterById(parameter.getParameter().getId()).setValue(isValue);
                        break;
                    case TYPE_RIGHT:
                        memParameterList.get(SideSelect.S_UNILATERAL_RIGHT).getHAParameterById(parameter.getParameter().getId()).setValue(isValue);
                        break;
                    default:
                        break;
                }
            }
        });

        allParamListView = findViewById(R.id.list_param);
        allParamListView.setAdapter(paramInfoAdapter);
        allParamListView.setVisibility(View.GONE);

        deviceInfoAdapter = new DeviceInfoAdapter(this);
        deviceInfoAdapter.setConnectPosition(DeviceInfoAdapter.INVALID_POSITION);
        deviceInfoAdapter.setOnDeviceClickListener(new DeviceInfoAdapter.OnDeviceClickListener() {
            @Override
            public void onChoice(int position) {
                DeviceInfo deviceInfo = deviceInfoAdapter.getItem(position);
                if (deviceManager.getDeviceState(deviceInfo) == null) {
                    connPos = position;
                    currentMemory = -1;
                    deviceManager.connectDevice(deviceInfo);
                    Toast.makeText(MainActivity.this, getString(R.string.connecting), Toast.LENGTH_SHORT).show();
                } else {
                    if (deviceManager.disconnectDevice(deviceInfo) == ErrorType.SUCCESS)
                        Toast.makeText(MainActivity.this, getString(R.string.disconnecting), Toast.LENGTH_SHORT).show();
                    else {
                        disconnectProcess();
                    }
                }
            }
        });
        ListView deviceInfoListView = findViewById(R.id.list_device);
        deviceInfoListView.setAdapter(deviceInfoAdapter);
    }

    private void fillAllParameterList() {
        if (allParameterList == null)
            allParameterList = new ArrayList<>();
        else
            allParameterList.clear();

        if (curState.getHaSide() == SideSelect.S_BINAURAL) {
            for (HAParameter parameter : sysParameterList.get(0).getParameterList()) {
                AllParameter tmp = new AllParameter(parameter, TYPE_SYS);
                allParameterList.add(tmp);
            }

            for (HAParameter parameter : memParameterList.get(SideSelect.S_UNILATERAL_LEFT).getParameterList()) {
                AllParameter tmp = new AllParameter(parameter, TYPE_LEFT);
                allParameterList.add(tmp);
            }

            for (HAParameter parameter : memParameterList.get(SideSelect.S_UNILATERAL_RIGHT).getParameterList()) {
                AllParameter tmp = new AllParameter(parameter, TYPE_RIGHT);
                allParameterList.add(tmp);
            }
        }
        paramInfoAdapter.setAllParameterList(allParameterList);
    }

    private void disconnectProcess() {
        deviceInfoAdapter.setConnectPosition(DeviceInfoAdapter.INVALID_POSITION);
        operation_table_layout.setVisibility(View.GONE);
        allParamListView.setVisibility(View.GONE);
        paramInfoAdapter.parameterListClear();
        deviceInfoAdapter.deviceListClear();

        currentMemory = -1;
        sysParameterList = null;
        memParameterList = null;
    }

    private void prepareHearingTest() {
        pureToneManager = new PureToneManager(MainActivity.this);
        left_hl = new int[FittingAlgorithm.FA_HL_ARR_SIZE];
        Arrays.fill(left_hl, FittingAlgorithm.INVALID_HL_VAL);
        right_hl = new int[FittingAlgorithm.FA_HL_ARR_SIZE];
        Arrays.fill(right_hl, FittingAlgorithm.INVALID_HL_VAL);

        pureToneManager.prepare_gen_tone();
    }

    private void deviceManagerInit() {
        deviceManager = HADeviceManager.getInstance();
        detectCallback = new DetectCallback() {
            @Override
            public void onDetecting(DeviceInfo deviceInfo) {
                deviceInfoAdapter.addDeviceInfo(deviceInfo);
            }

            @Override
            public void onDetectFinish(List<DeviceInfo> deviceInfoList) {
                if (btn_scan.getText().equals(getString(R.string.scanning)))
                    btn_scan.setText(getString(R.string.start_scan));

                deviceInfoAdapter.setDeviceInfoList(deviceInfoList);
            }

            @Override
            public void onFail(int error) {
                if (btn_scan.getText().equals(getString(R.string.scanning)))
                    btn_scan.setText(getString(R.string.start_scan));
                Toast.makeText(MainActivity.this, "detect fail:" + getErrorString(error), Toast.LENGTH_LONG).show();
            }
        };

        connectCallback = new ConnectCallback() {
            @Override
            public void onConnectSuccess() {
                deviceInfoAdapter.setConnectPosition(connPos);
                operation_table_layout.setVisibility(View.VISIBLE);
                allParamListView.setVisibility(View.VISIBLE);
                curState = deviceManager.getDeviceState(deviceInfoAdapter.getItem(connPos));

                Toast.makeText(MainActivity.this, curState.getDevName(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onDisconnect(boolean active) {
                disconnectProcess();
                Toast.makeText(MainActivity.this, active? R.string.active_disconnected:
                        R.string.passive_disconnected, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFail(int error) {
                Toast.makeText(MainActivity.this, "connect fail:"+ getErrorString(error), Toast.LENGTH_SHORT).show();
            }
        };

        configCallback = new ConfigCallback() {
            @Override
            public void onSuccess(int op) {
                Toast.makeText(MainActivity.this,getOperationString(op)+" success", Toast.LENGTH_SHORT).show();
                switch(op) {
                    case OperationType.OP_AUTO_CHECK:
                    case OperationType.OP_SELECT_MEMORY:
                        deviceManager.readDevice();
                        break;
                    case OperationType.OP_READ_CONFIG:
                        currentMemory = deviceManager.getCurrentMemory();
                        sysParameterList = deviceManager.getSysParameterList();
                        memParameterList = deviceManager.getMemBinParameterList();
                        et_mem_choice.setText(String.valueOf(currentMemory));
                        fillAllParameterList();
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onFail(int op, int error) {
                Toast.makeText(MainActivity.this,getOperationString(op)+getErrorString(error), Toast.LENGTH_SHORT).show();
                switch(op) {
                    case OperationType.OP_AUTO_CHECK:
                        deviceManager.disconnectDevice(deviceInfoAdapter.getItem(connPos));
                        break;
                    case OperationType.OP_READ_CONFIG:
                        break;
                    default:
                        break;
                }
            }
        };

        apiCheck();
    }

    private void apiCheck() {
        int result = deviceManager.init(this.getApplication(), detectCallback, connectCallback, configCallback);
        if (result != ErrorType.SUCCESS) {
            Toast.makeText(this, getErrorString(result), Toast.LENGTH_LONG).show();
            return;
        }

        List<String> supportDeviceList = deviceManager.getSupportDeviceList();
        Toast.makeText(this, "API: " + deviceManager.getAPIVersion()+ " Support: "+supportDeviceList.toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_scan:
                if (btn_scan.getText().equals(getString(R.string.start_scan))) {
                    deviceInfoAdapter.deviceListClear();
                    btn_scan.setText(getString(R.string.scanning));
                    deviceManager.detectDevice();
                    Toast.makeText(MainActivity.this, getString(R.string.start_scan), Toast.LENGTH_SHORT).show();
                } else if (btn_scan.getText().equals(getString(R.string.scanning))) {
                    Toast.makeText(MainActivity.this, getString(R.string.scanning), Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.btn_set_mem:
                if (et_mem_choice.getText().toString().equals("")) {
                    Toast.makeText(MainActivity.this, getString(R.string.no_mem_num), Toast.LENGTH_SHORT).show();
                    return;
                }
                int num = Integer.valueOf(et_mem_choice.getText().toString());
                deviceManager.setCurrentSelect(num);
                break;
            case R.id.btn_set_sys_param:
                deviceManager.writeDevice(0);
                break;
            case R.id.btn_set_mem_param:
                deviceManager.writeDevice(currentMemory);
                break;

            case R.id.btn_save_hl:
                int index = getPureToneIndex();
                if (index < 0 || index >= FittingAlgorithm.FA_HL_ARR_SIZE)
                    return;
//                left_hl[index] = getPureToneDb(SideSelect.S_UNILATERAL_LEFT);
//                right_hl[index] = getPureToneDb(SideSelect.S_UNILATERAL_RIGHT);
                TextView left_hl_view, right_hl_view;
                switch(index) {
                    case 0:
                        left_hl_view = findViewById(R.id.txt_l125_hl);
                        right_hl_view = findViewById(R.id.txt_r125_hl);
                        break;
                    case 1:
                        left_hl_view = findViewById(R.id.txt_l250_hl);
                        right_hl_view = findViewById(R.id.txt_r250_hl);
                        break;
                    case 2:
                        left_hl_view = findViewById(R.id.txt_l500_hl);
                        right_hl_view = findViewById(R.id.txt_r500_hl);
                        break;
                    case 3:
                        left_hl_view = findViewById(R.id.txt_l750_hl);
                        right_hl_view = findViewById(R.id.txt_r750_hl);
                        break;
                    case 4:
                        left_hl_view = findViewById(R.id.txt_l1000_hl);
                        right_hl_view = findViewById(R.id.txt_r1000_hl);
                        break;
                    case 5:
                        left_hl_view = findViewById(R.id.txt_l1500_hl);
                        right_hl_view = findViewById(R.id.txt_r1500_hl);
                        break;
                    case 6:
                        left_hl_view = findViewById(R.id.txt_l2000_hl);
                        right_hl_view = findViewById(R.id.txt_r2000_hl);
                        break;
                    case 7:
                        left_hl_view = findViewById(R.id.txt_l3000_hl);
                        right_hl_view = findViewById(R.id.txt_r3000_hl);
                        break;
                    case 8:
                        left_hl_view = findViewById(R.id.txt_l4000_hl);
                        right_hl_view = findViewById(R.id.txt_r4000_hl);
                        break;
                    case 9:
                        left_hl_view = findViewById(R.id.txt_l6000_hl);
                        right_hl_view = findViewById(R.id.txt_r6000_hl);
                        break;
                    case 10:
                        left_hl_view = findViewById(R.id.txt_l8000_hl);
                        right_hl_view = findViewById(R.id.txt_r8000_hl);
                        break;
                    default:
                        return;
                }
                left_hl_view.setText(String.valueOf(left_hl[index]));
                right_hl_view.setText(String.valueOf(right_hl[index]));
                break;
            case R.id.btn_auto_fitting:
                deviceManager.autoFitting(FittingAlgorithm.FA_BSL, SideSelect.S_UNILATERAL_LEFT, left_hl);
                deviceManager.autoFitting(FittingAlgorithm.FA_BSL, SideSelect.S_UNILATERAL_RIGHT, right_hl);
                break;
            case R.id.btn_read_param:
                deviceManager.readDevice();
                break;

            case R.id.btn_freq_left:
                pureToneManager.start(SideSelect.S_UNILATERAL_LEFT, getPureToneFreq(), getPureToneDb(SideSelect.S_UNILATERAL_LEFT));
                break;
            case R.id.btn_freq_right:
                pureToneManager.start(SideSelect.S_UNILATERAL_RIGHT, getPureToneFreq(), getPureToneDb(SideSelect.S_UNILATERAL_RIGHT));
                break;
            default:
                break;
        }
    }

    private int getPureToneIndex() {
        Spinner spn_pure_tone = findViewById(R.id.spn_pure_tone);
        return spn_pure_tone.getSelectedItemPosition();
    }

    private int getPureToneFreqByIndex(int index) {
        switch(index) {
            case 0:
            case 1:
                return (1+index) * 125;
            case 2:
            case 3:
            case 4:
                return index*250;
            case 5:
            case 6:
                return (index-2)*500;
            case 7:
            case 8:
                return (index-4)*1000;
            case 9:
            case 10:
                return (index-6)*2000;
            default:
                return 8000;
        }
    }

    private int getPureToneFreq() {
        return getPureToneFreqByIndex(getPureToneIndex());
    }

    private int getPureToneDb(int side) {
        EditText et_tone_db;
        if (side == SideSelect.S_UNILATERAL_LEFT)
            et_tone_db = findViewById(R.id.et_tone_left);
        else
            et_tone_db = findViewById(R.id.et_tone_right);
        String dbString = et_tone_db.getText().toString();
        if (!dbString.isEmpty()) {
            int dbVal = Integer.valueOf(dbString);
            if (dbVal >= FittingAlgorithm.MIN_HL_VAL && dbVal <= FittingAlgorithm.MAX_HL_VAL)
                return dbVal;
        }
        return FittingAlgorithm.INVALID_HL_VAL;
    }

    private String getOperationString(int op) {
        switch (op) {
            case OperationType.OP_AUTO_CHECK:
                return "AUTO_CHECK";
            case OperationType.OP_READ_CONFIG:
                return "READ_CONFIG";
            case OperationType.OP_SELECT_MEMORY:
                return "SELECT_MEMORY";
            case OperationType.OP_WRITE_SYSTEM:
                return "WRITE_SYSTEM";
            case OperationType.OP_WRITE_MEMORY:
                return "WRITE_MEMORY";
            case OperationType.OP_AUTO_FITTING:
                return "AUTO_FITTING";
            default:
                return "UNKNOWN";
        }
    }

    private String getErrorString(int error) {
        switch(error){
            case ErrorType.SUCCESS:
                return " Success";

            case ErrorType.E_UNINITIALIZED:
                return " X API uninitialized";
            case ErrorType.E_INVALID_INPUT_PARAMS:
                return " X Invalid input parameters";
            case ErrorType.E_NO_MORE_DEVICE:
                return " X No more can connect";
            case ErrorType.E_REPEAT_INITIALIZED:
                return " X Repeat initialized";

            case ErrorType.E_BLUETOOTH_UNSUPPORTED:
                return " X BT unsupported";
            case ErrorType.E_BLUETOOTH_DISABLED:
                return " X BT disable";
            case ErrorType.E_LOCATION_DISABLED:
                return " X Location disabled";

            case ErrorType.E_BLE_START_SCAN_FAILED:
                return " X BLE scan failed";
            case ErrorType.E_BLE_NO_DEVICE_SCANNED:
                return " X BLE not scanned";
            case ErrorType.E_BLE_NO_DEVICE_CONNECTED:
                return " X BLE not connected";
            case ErrorType.E_BLE_SERVICE_UNSUPPORTED:
                return " X BLE service unrecognized";
            case ErrorType.E_BLE_COMMUNICATION:
                return " X BLE communication";

            case ErrorType.E_CONFIG_IN_PROGRESS:
                return " X Config in progress";
            case ErrorType.E_PARAMETER_NULL:
                return " X No parameter";
            default:
                break;
        }
        return " Unknown";
    }
}

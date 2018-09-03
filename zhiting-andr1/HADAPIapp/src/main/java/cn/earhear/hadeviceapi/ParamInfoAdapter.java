package cn.earhear.hadeviceapi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

import java.util.List;

import cn.earhear.hadevicelib.HAParameter;
import cn.earhear.hadevicelib.constant.ParameterType;

/**
 * Created by ice88 on 2018-5-23.
 */

public class ParamInfoAdapter extends BaseAdapter {
    private List<AllParameter> allParamList;
    private Context context;

    private OnValueChangeListener valueChangeListener;
    private OnBooleanValueChangeListener booleanValueChangeListener;

    public ParamInfoAdapter(Context context) {
        this.context = context;
    }

    public List<AllParameter> getAllParameterList() {
        return allParamList;
    }

    public void setAllParameterList(List<AllParameter> parameter) {
        this.allParamList = parameter;
        notifyDataSetChanged();
    }

    public void parameterListClear() {
        if (allParamList != null) {
            this.allParamList.clear();
            notifyDataSetChanged();
        }
    }

    @Override
    public int getCount() {
        if (allParamList != null)
            return allParamList.size();
        return 0;
    }

    @Override
    public AllParameter getItem(int position) {
        if (allParamList == null || position > allParamList.size())
            return null;
        return allParamList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView != null) {
            holder = (ViewHolder) convertView.getTag();
        } else {
            convertView = LayoutInflater.from(context).inflate(R.layout.adapter_paraminfo, parent, false);
            holder = new ViewHolder();
            convertView.setTag(holder);
            holder.txt_type = convertView.findViewById(R.id.txt_type);
            holder.txt_param = convertView.findViewById(R.id.txt_param);
            holder.bar_param = convertView.findViewById(R.id.param_seekbar);
            holder.sw_param = convertView.findViewById(R.id.param_switch);
            holder.txt_value = convertView.findViewById(R.id.txt_value);
        }

        final AllParameter allTypeParam = getItem(position);
        if (allTypeParam != null) {
            HAParameter param = allTypeParam.getParameter();
            holder.txt_type.setText(allTypeParam.getType());
            holder.txt_param.setText(param.getId());

            int data_type = param.getType();
            if (data_type != ParameterType.T_BOOLEAN) {
                holder.sw_param.setVisibility(View.GONE);
                holder.bar_param.setVisibility(View.VISIBLE);
                holder.bar_param.setMax(param.getMax());
                holder.bar_param.setProgress(param.getValue());
                holder.bar_param.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        if (valueChangeListener != null)
                            valueChangeListener.onValueChanging(position, progress);
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                        if (valueChangeListener != null) {
                            valueChangeListener.onValueChanged(position, seekBar.getProgress());
                            notifyDataSetChanged();
                        }
                    }
                });
            } else {
                holder.bar_param.setVisibility(View.GONE);
                holder.sw_param.setVisibility(View.VISIBLE);
                holder.sw_param.setChecked(param.getBooleanValue());
                holder.sw_param.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (booleanValueChangeListener != null)
                            booleanValueChangeListener.onBooleanChanged(position, isChecked);
                        notifyDataSetChanged();
                    }
                });
            }

            switch (data_type) {
                case ParameterType.T_BOOLEAN:
                    holder.txt_value.setText(String.valueOf(param.getBooleanValue()));
                    break;
                case ParameterType.T_DOUBLE:
                    holder.txt_value.setText(String.valueOf(param.getDoubleValue()));
                    break;
                case ParameterType.T_INDEXED_LIST:
                    holder.txt_value.setText(String.valueOf(param.getIndexedList().get(param.getValue())));
                    break;
                case ParameterType.T_INDEXED_TEXT_LIST:
                    holder.txt_value.setText(String.valueOf(param.getIndexedTextList().get(param.getValue())));
                    break;
                case ParameterType.T_INT:
                    holder.txt_value.setText(String.valueOf(param.getValue()));
                    break;
                default:
                    break;
            }
        }
        return convertView;
    }

    public interface OnValueChangeListener {
        void onValueChanging(int pos, int value);
        void onValueChanged(int pos, int value);
    }

    public void setOnValueChangeListener(OnValueChangeListener listener) {
        this.valueChangeListener = listener;
    }

    public interface OnBooleanValueChangeListener {
        void onBooleanChanged(int pos, boolean isValue);
    }

    public void setOnBooleanValueChangeListener(OnBooleanValueChangeListener listener) {
        this.booleanValueChangeListener = listener;
    }

    static class ViewHolder {
        TextView txt_type;
        TextView txt_param;
        SeekBar bar_param;
        Switch sw_param;
        TextView txt_value;
    }
}

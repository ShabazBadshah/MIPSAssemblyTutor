package com.example.badsh.mipsassemblytutor.models;

import com.example.badsh.mipsassemblytutor.engine.Utils.EngineUtils;

public class Register {

    private int valueInRegister;
    private String registerName;

    public Register(String registerName, int valueStored) {
        this.valueInRegister = valueStored;
        this.registerName = registerName;
    }

    public String getRegisterName() {
        return this.registerName;
    }

    public int getStoredValue() {
        return this.valueInRegister;
    }

    public String getRegisterNumBinaryRep(int amountPaddingLeft) {

        String registerNum = this.registerName.substring(2, this.registerName.length());
        String binaryRepOfString = Integer.toBinaryString(Integer.valueOf(registerNum));

        return EngineUtils.leftPadBinaryString(amountPaddingLeft, binaryRepOfString);
    }

    public void setStoredValue(int valueToSet) { this.valueInRegister = valueToSet; }

    @Override
    public String toString() {
        return new StringBuilder()
                .append(this.getRegisterName())
                .append("=")
                .append(this.valueInRegister)
                .toString();
    }

}

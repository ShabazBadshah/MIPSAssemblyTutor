package com.example.badsh.mipsassemblytutor.models;

import com.example.badsh.mipsassemblytutor.engine.Utils.EngineUtils;

/*
Models a register in MIPS, contains a value and a name
 */
public class Register {

    private int mValueInRegister;
    private String mCompleteRegisterName;

    public Register(String registerName, int valueStored) {
        this.mValueInRegister = valueStored;
        this.mCompleteRegisterName = registerName;
    }

    public String getCompleteRegisterName() {
        return this.mCompleteRegisterName;
    }

    public int getStoredValue() {
        return this.mValueInRegister;
    }

    public void setStoredValue(int valueToSet) { this.mValueInRegister = valueToSet; }

    public String getRegisterNumBinaryRep(int amountPaddingLeft) {
        String registerIndex = this.mCompleteRegisterName.substring(2, this.mCompleteRegisterName.length());
        String binaryRepOfString = Integer.toBinaryString(Integer.valueOf(registerIndex));

        return EngineUtils.leftPadBinaryString(amountPaddingLeft, binaryRepOfString);
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append(this.mCompleteRegisterName)
                .append("=")
                .append(this.mValueInRegister)
                .toString();
    }

}

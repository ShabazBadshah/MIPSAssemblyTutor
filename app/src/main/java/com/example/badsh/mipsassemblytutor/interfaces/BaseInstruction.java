package com.example.badsh.mipsassemblytutor.interfaces;

import com.example.badsh.mipsassemblytutor.models.MipsCommand;
import com.example.badsh.mipsassemblytutor.models.Register;

import java.util.ArrayList;

public interface BaseInstruction {

    String getQuestion();
    String getAnswer();
    String getFunctionName();
    ArrayList<Register> getRegisters();
    MipsCommand getAssociatedCommand();
    String getGeneratedMipsCommand();
    String getMachineInstruction();
}

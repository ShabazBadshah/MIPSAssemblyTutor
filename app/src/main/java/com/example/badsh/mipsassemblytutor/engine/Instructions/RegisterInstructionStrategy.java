package com.example.badsh.mipsassemblytutor.engine.Instructions;

import com.example.badsh.mipsassemblytutor.interfaces.BaseInstruction;
import com.example.badsh.mipsassemblytutor.models.MipsCommand;
import com.example.badsh.mipsassemblytutor.models.Register;

import java.util.ArrayList;

public class RegisterInstructionStrategy implements BaseInstruction {

    private MipsCommand mipsCommand;

    public RegisterInstructionStrategy(MipsCommand mipsCommand) {
        this.mipsCommand = mipsCommand;
    }

    public String getFunctionName() { return this.mipsCommand.getCommandFunctionString(); }
    public ArrayList<Register> getRegisters() { return this.mipsCommand.getCommandRegisters(); }
    public String getMachineInstruction() { return this.mipsCommand.getCommandMachineInstruction(); }
    public String getQuestion() { return this.mipsCommand.getCommandQuestion(); }
    public String getAnswer() { return this.mipsCommand.getCommandAnswer(); }
    public String toString() { return this.mipsCommand.toString(); }
    public MipsCommand getAssociatedCommand() { return this.mipsCommand; }

    @Override
    public String getGeneratedMipsCommand() { return this.mipsCommand.getCommandGeneratedInstruction(); }

}


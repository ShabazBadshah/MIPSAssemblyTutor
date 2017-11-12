package com.example.badsh.mipsassemblytutor.engine.Instructions;

import com.example.badsh.mipsassemblytutor.interfaces.BaseImmediateInstruction;
import com.example.badsh.mipsassemblytutor.models.MipsCommand;
import com.example.badsh.mipsassemblytutor.models.MipsImmediateCommand;
import com.example.badsh.mipsassemblytutor.models.Register;

import java.util.ArrayList;

public class ImmediateInstructionStrategy implements BaseImmediateInstruction {

    private MipsImmediateCommand mipsCommand;

    public ImmediateInstructionStrategy(MipsImmediateCommand mipsImmediateCommand) {
        this.mipsCommand = mipsImmediateCommand;
    }

    public int getImmediateValue() { return this.mipsCommand.getCommandImmediateValue(); }
    public String getFunctionName() { return this.mipsCommand.getCommandFunctionString(); }
    public ArrayList<Register> getRegisters() { return this.mipsCommand.getCommandRegisters(); }
    public String getMachineInstruction() { return this.mipsCommand.getCommandMachineInstruction(); }
    public String getQuestion() { return this.mipsCommand.getCommandQuestion(); }
    public String getAnswer() { return this.mipsCommand.getCommandAnswer(); }
    public String toString() { return this.mipsCommand.toString(); }
    public MipsCommand getAssociatedCommand() { return this.mipsCommand; }

}


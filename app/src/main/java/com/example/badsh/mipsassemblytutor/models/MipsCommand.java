package com.example.badsh.mipsassemblytutor.models;

import java.util.ArrayList;

public class MipsCommand {

    protected String FUNCTION_STRING;
    protected String ANSWER;
    protected String QUESTION;
    protected String FUNCTION_OPCODE;
    protected String COMMAND;
    protected String machineInstruction;
    protected ArrayList<Register> questionRegisters = new ArrayList<>(4);

    public String getCommandAnswer() { return this.ANSWER; }
    public String getCommandQuestion() { return this.QUESTION; }
    public String getCommandGeneratedInstruction() { return this.COMMAND; }
    public ArrayList<Register> getCommandRegisters() { return this.questionRegisters; }
    public String getCommandFunctionString() { return this.FUNCTION_STRING; }
    public String getCommandOpcode() { return this.FUNCTION_OPCODE; }
    public String getCommandMachineInstruction() { return this.machineInstruction; }

    public String toString() {
        return new StringBuilder()
                .append(this.QUESTION)
                .append(" ")
                .append(this.ANSWER)
                .toString();
    }

}

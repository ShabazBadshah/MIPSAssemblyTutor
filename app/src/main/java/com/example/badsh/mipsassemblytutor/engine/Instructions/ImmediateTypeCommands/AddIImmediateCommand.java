package com.example.badsh.mipsassemblytutor.engine.Instructions.ImmediateTypeCommands;

import com.example.badsh.mipsassemblytutor.data_provider.QuizDataProvider;
import com.example.badsh.mipsassemblytutor.models.MipsImmediateCommand;
import com.example.badsh.mipsassemblytutor.engine.Utils.EngineUtils;
import com.example.badsh.mipsassemblytutor.models.Register;

public class AddIImmediateCommand extends MipsImmediateCommand {

    private Register answerRegister;

    public AddIImmediateCommand() {
        FUNCTION_STRING = "addi";
        FUNCTION_OPCODE = "001000";
        // Generates registers
        questionRegisters.add(QuizDataProvider.getRandomRegister());
        questionRegisters.add(QuizDataProvider.getRandomRegister());

        // Generates the immediate value
        IMMEDIATE_VALUE = EngineUtils.generateRandomDecimalNumber(true);

        this.buildInstruction();
        this.computeInstruction();
        this.buildRegisterMachineInstruction();
    }

    private void computeInstruction() {
        int computedAnswer = questionRegisters.get(1).getStoredValue() + IMMEDIATE_VALUE;
        ANSWER = String.valueOf(computedAnswer);
    }

    private void buildRegisterMachineInstruction() {
        machineInstruction = new StringBuilder()
                .append(FUNCTION_OPCODE)
                .append(questionRegisters.get(0).getRegisterNumBinaryRep(5))
                .append(questionRegisters.get(1).getRegisterNumBinaryRep(5))
                .append(EngineUtils.leftPadBinaryString(16, String.valueOf(
                        Integer.toBinaryString(IMMEDIATE_VALUE))))
                .toString();
    }

    private void buildInstruction() {
        String SPACE = " ";
        String COMMA = ", ";

        COMMAND = new StringBuilder()
                .append(FUNCTION_STRING)
                .append(SPACE)
                .append(questionRegisters.get(0).getRegisterName()) // The first register
                .append(COMMA)
                .append(questionRegisters.get(1).getRegisterName()) // The second register
                .append(COMMA)
                .append(String.valueOf(IMMEDIATE_VALUE))
                .toString();

        QUESTION = new StringBuilder()
                .append(questionRegisters.get(1).toString())
                .append(".\n")
                .append("Compute the following command\n")
                .append(COMMAND)
                .toString(); // The immediate value
    }
}

package com.example.badsh.mipsassemblytutor.engine.Instructions.RegisterInstructionCommands;

import com.example.badsh.mipsassemblytutor.data_provider.QuizDataProvider;
import com.example.badsh.mipsassemblytutor.models.MipsCommand;
import com.example.badsh.mipsassemblytutor.engine.Utils.EngineUtils;

public class AddRegisterCommand extends MipsCommand {

    public AddRegisterCommand() {
        FUNCTION_STRING = "add";
        FUNCTION_OPCODE = "100000";
        // Generates registers
        questionRegisters.add(QuizDataProvider.getRandomRegister());
        questionRegisters.add(QuizDataProvider.getRandomRegister());
        questionRegisters.add(QuizDataProvider.getRandomRegister());

        questionRegisters.get(2).setStoredValue(EngineUtils.generateRandomDecimalNumber(true, 16));

        this.buildInstruction();
        this.computeInstruction();
        this.buildRegisterMachineInstruction();
    }

    private void computeInstruction() {
        int computedAnswer = questionRegisters.get(1).getStoredValue()
                + questionRegisters.get(2).getStoredValue();
        ANSWER = String.valueOf(computedAnswer);
    }

    private void buildRegisterMachineInstruction() {
        machineInstruction = new StringBuilder()
                .append("000000")
                .append(questionRegisters.get(0).getRegisterNumBinaryRep(5))
                .append(questionRegisters.get(1).getRegisterNumBinaryRep(5))
                .append(questionRegisters.get(2).getRegisterNumBinaryRep(5))
                .append("00000")
                .append(FUNCTION_OPCODE)
                .toString();
    }

    private void buildInstruction() {
        String SPACE = " ";
        String COMMA = ", ";

        COMMAND = new StringBuilder()
                .append(FUNCTION_STRING)
                .append(SPACE)
                .append(questionRegisters.get(0).getRegisterName()) // The destination register
                .append(COMMA)
                .append(questionRegisters.get(1).getRegisterName()) // The first register
                .append(COMMA)
                .append(questionRegisters.get(2).getRegisterName()) // The second register
                .toString();

        QUESTION = new StringBuilder()
                .append(questionRegisters.get(2).toString())
                .append(SPACE)
                .append(questionRegisters.get(1).toString())
                .append(".\n")
                .append(" Compute the following command\n")
                .append(COMMAND)
                .toString();
    }
}

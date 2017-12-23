package com.example.badsh.mipsassemblytutor.engine.Instructions.ImmediateTypeCommands;

import com.example.badsh.mipsassemblytutor.data_provider.QuizDataProvider;
import com.example.badsh.mipsassemblytutor.models.MipsImmediateCommand;
import com.example.badsh.mipsassemblytutor.engine.Utils.EngineUtils;

public class XorIImmediateCommand extends MipsImmediateCommand {

    public XorIImmediateCommand() {
        FUNCTION_STRING = "xori";
        FUNCTION_OPCODE = "001110";
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
        ANSWER = EngineUtils.convertDecimalToBinary(questionRegisters.get(1).getStoredValue()
                ^ IMMEDIATE_VALUE);
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
                .append(questionRegisters.get(0).getCompleteRegisterName()) // The first register
                .append(COMMA)
                .append(questionRegisters.get(1).getCompleteRegisterName()) // The second register
                .append(COMMA)
                .append(IMMEDIATE_VALUE)
                .toString();

        QUESTION = new StringBuilder()
                .append(questionRegisters.get(1).toString())
                .append(".")
                .append(questionRegisters.get(1).getCompleteRegisterName())
                .append(" in binary is: ")
                .append(EngineUtils.convertDecimalToBinary(questionRegisters.get(1).getStoredValue()))
                .append(".\n")
                .append(" Compute the following command\n")
                .append(COMMAND)
                .toString(); // The immediate value
    }
}

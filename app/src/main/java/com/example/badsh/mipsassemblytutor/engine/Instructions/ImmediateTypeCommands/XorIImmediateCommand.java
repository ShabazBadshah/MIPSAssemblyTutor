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
        StringBuilder sb = new StringBuilder()
                .append(FUNCTION_OPCODE)
                .append(questionRegisters.get(0).getRegisterNumBinaryRep(5))
                .append(questionRegisters.get(1).getRegisterNumBinaryRep(5))
                .append(EngineUtils.leftPadBinaryString(16, String.valueOf(
                        Integer.toBinaryString(IMMEDIATE_VALUE))));
        // converts machine instruction into 4 segments with 8 bits per segment
        machineInstruction = EngineUtils.segmentBinaryStringNPieces(8, sb.toString());
    }

    private void buildInstruction() {
        String SPACE = " ";
        String COMMA = ", ";

        QUESTION = new StringBuilder()
                .append(questionRegisters.get(1).toString())
                .append(".")
                .append(questionRegisters.get(1).getRegisterName())
                .append(" in binary is: ")
                .append(EngineUtils.convertDecimalToBinary(questionRegisters.get(1).getStoredValue()))
                .append(".\n")
                .append(" Compute the following command\n")
                .append(FUNCTION_STRING)
                .append(SPACE)
                .append(questionRegisters.get(0).getRegisterName()) // The first register
                .append(COMMA)
                .append(questionRegisters.get(1).getRegisterName()) // The second register
                .append(COMMA)
                .append(IMMEDIATE_VALUE)
                .toString(); // The immediate value
    }
}

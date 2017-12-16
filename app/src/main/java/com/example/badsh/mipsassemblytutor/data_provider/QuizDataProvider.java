package com.example.badsh.mipsassemblytutor.data_provider;

import com.example.badsh.mipsassemblytutor.engine.Instructions.ImmediateInstructionStrategy;
import com.example.badsh.mipsassemblytutor.engine.Instructions.ImmediateTypeCommands.AddIImmediateCommand;
import com.example.badsh.mipsassemblytutor.engine.Instructions.ImmediateTypeCommands.AndIImmediateCommand;
import com.example.badsh.mipsassemblytutor.engine.Instructions.ImmediateTypeCommands.OrIImmediateCommand;
import com.example.badsh.mipsassemblytutor.engine.Instructions.ImmediateTypeCommands.XorIImmediateCommand;
import com.example.badsh.mipsassemblytutor.engine.Instructions.RegisterInstructionCommands.AddRegisterCommand;
import com.example.badsh.mipsassemblytutor.engine.Instructions.RegisterInstructionCommands.ShiftLeftLogicalRegisterCommand;
import com.example.badsh.mipsassemblytutor.engine.Instructions.RegisterInstructionCommands.ShiftRightLogicalRegisterCommand;
import com.example.badsh.mipsassemblytutor.engine.Instructions.RegisterInstructionCommands.SubRegisterCommand;
import com.example.badsh.mipsassemblytutor.engine.Instructions.RegisterInstructionStrategy;
import com.example.badsh.mipsassemblytutor.engine.Utils.EngineUtils;
import com.example.badsh.mipsassemblytutor.interfaces.BaseInstruction;
import com.example.badsh.mipsassemblytutor.models.MipsCommand;
import com.example.badsh.mipsassemblytutor.models.Register;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class QuizDataProvider {

    private static int previousRegister = 0;

    private static final String[] tempRegisters = {
            "$t0",
            "$t1",
            "$t2",
            "$t3",
            "$t4",
            "$r5",
            "$t6",
            "$t7",
            "$t8",
            "$t9"
    };

    private static final BaseInstruction[] allMipsInstructions = {
            new ImmediateInstructionStrategy(new AddIImmediateCommand()),
            new ImmediateInstructionStrategy(new XorIImmediateCommand()),
            new ImmediateInstructionStrategy(new AndIImmediateCommand()),
            new ImmediateInstructionStrategy(new OrIImmediateCommand()),

            new RegisterInstructionStrategy(new AddRegisterCommand()),
            new RegisterInstructionStrategy(new SubRegisterCommand()),
            new RegisterInstructionStrategy(new ShiftRightLogicalRegisterCommand()),
            new RegisterInstructionStrategy(new ShiftLeftLogicalRegisterCommand())
    };

    private static final BaseInstruction[] mipsImmediateInstructions = {
            new ImmediateInstructionStrategy(new AddIImmediateCommand()),
            new ImmediateInstructionStrategy(new XorIImmediateCommand()),
            new ImmediateInstructionStrategy(new AndIImmediateCommand()),
            new ImmediateInstructionStrategy(new OrIImmediateCommand()),
    };

    public static Map<String, Class> functionOpcodes = new HashMap<String, Class>(){{
        this.put("add", AddRegisterCommand.class);
        this.put("sll", ShiftLeftLogicalRegisterCommand.class);
        this.put("srl", ShiftRightLogicalRegisterCommand.class);
        this.put("sub", SubRegisterCommand.class);

        this.put("addi", AddIImmediateCommand.class);
        this.put("andi", AndIImmediateCommand.class);
        this.put("ori",  OrIImmediateCommand.class);
        this.put("xori", XorIImmediateCommand.class);
    }};

    public static BaseInstruction getRandomImmediateCommand() {
        return mipsImmediateInstructions[new Random().nextInt(mipsImmediateInstructions.length)];
    }

    public static Register getRandomRegister() {
        int randValue = EngineUtils.generateRandomDecimalNumber(true);
        int randRegisterToPick = EngineUtils.randNumGen.nextInt(tempRegisters.length);

        while (previousRegister == randRegisterToPick) { // Try picking a register that you didn't use
            randRegisterToPick = EngineUtils.randNumGen.nextInt(tempRegisters.length);
        }

        String registerPicked = tempRegisters[randRegisterToPick];
        previousRegister = randRegisterToPick;

        return new Register(registerPicked, randValue);
    }

    public static BaseInstruction getRandomFunction() {
        return allMipsInstructions[new Random().nextInt(allMipsInstructions.length)];
    }

    public static MipsCommand getSpecificFunction(String functionName) {
        try {
            return (MipsCommand) functionOpcodes.get(functionName).newInstance();
        } catch (InstantiationException e) {
            return null;
        } catch (IllegalAccessException e) {
            return null;
        }
    }

}

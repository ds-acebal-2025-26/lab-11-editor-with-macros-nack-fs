package es.uniovi.eii.ds.instruction;

import java.util.ArrayList;
import java.util.List;

public class Macro implements Instruction{
	private List<Instruction> instructions = new ArrayList<>();

	public Macro(List<Instruction> instructions) {
		this.instructions = instructions;
	}

	@Override
	public void execute() {
		for(Instruction i : instructions) {
			i.execute();
		}
	}

}

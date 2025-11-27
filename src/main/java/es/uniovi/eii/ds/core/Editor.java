package es.uniovi.eii.ds.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import es.uniovi.eii.ds.instruction.Delete;
import es.uniovi.eii.ds.instruction.Insert;
import es.uniovi.eii.ds.instruction.Instruction;
import es.uniovi.eii.ds.instruction.Open;
import es.uniovi.eii.ds.instruction.Replace;

public class Editor {
	private Document document = new Document();
	private List<Instruction> macro = new ArrayList<>();
	private Map<String, List<Instruction>> macros = new HashMap<String, List<Instruction>>();
	private String macroName;
	
	public void open(String[] args) {
		isRecording(new Open(document, args));
	}
	
	public void insert(String[] args) {
		isRecording(new Insert(document, args));
	}
	
	public void delete() {
		isRecording(new Delete(document));
	}
	
	public void replace(String[] args) {
		isRecording(new Replace(document, args));
	}
	
	public void showText() {
		document.showText();
	}
	
	public void recordMacro(String name) {
		if(macroName==null) {
			macroName = name;
		}else {
			System.out.println("La macro: "+macroName+" sigue en curso, finaliza primero...");
		}
	}
	
	public void stopMacro() {
		if(macroName!=null) {
			macros.put(new String(macroName), new ArrayList<>(macro));
			macroName = null;
			macro = new ArrayList<>();
		}else {
			System.out.println("No existe ninguna grabación en curso...");
		}
	}
	
	public void executeMacro(String name) {
		List<Instruction> m = macros.get(name);
		if(m!=null) {
			for(Instruction i : m) {
				i.execute();
			}
		}else {
			System.out.println("No existe ninguna macro llamada: "+name);
		}
	}
	
	private void isRecording(Instruction instruction) {
		if(macroName!=null) {
			macro.add(instruction);
		}else {
			instruction.execute();
		}
	}
	
}

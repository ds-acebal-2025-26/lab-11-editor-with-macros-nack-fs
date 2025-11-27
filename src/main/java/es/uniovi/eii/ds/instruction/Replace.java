package es.uniovi.eii.ds.instruction;

import es.uniovi.eii.ds.core.Document;

public class Replace implements Instruction{
	private Document document;
	private String[] args;
	
	public Replace(Document document, String[] args) {
		this.document = document;
		this.args = args;
	}

	@Override
	public void execute() {
		document.replace(args);
	}
	
}

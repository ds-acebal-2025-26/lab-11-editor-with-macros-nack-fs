package es.uniovi.eii.ds.instruction;

import es.uniovi.eii.ds.core.Document;

public class Delete implements Instruction{
	private Document document;
	
	public Delete(Document document) {
		this.document = document;
	}

	@Override
	public void execute() {
		document.delete();
	}
	
}

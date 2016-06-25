package com.cheyooh.service.sdk.idata.cmccmm;

import java.util.ArrayList;
import java.util.List;

import org.nuxeo.common.xmap.annotation.XNodeList;
import org.nuxeo.common.xmap.annotation.XObject;

@XObject
public class XmlCmccmmProgramResults {
	@XNodeList(componentType = XmlCmccmmProgramResultSchema.class, type = ArrayList.class, value = "ProgramResult")
	private List<XmlCmccmmProgramResultSchema> ProgramResult ;

	public List<XmlCmccmmProgramResultSchema> getProgramResult() {
		return ProgramResult;
	}

	public void setProgramResult(List<XmlCmccmmProgramResultSchema> programResult) {
		ProgramResult = programResult;
	}

	@Override
	public String toString() {
		return "XmlCmccmmProgramResults [ProgramResult=" + ProgramResult + "]";
	}
	
	
}

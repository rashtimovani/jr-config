package net.rashack.externalvalues.implementation.convert;

public class PrimitiveCharConverter extends CharacterConverter {

	@Override
	Character defaultValue() {
		return '\u0000';
	}
}

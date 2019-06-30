package domain;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import domain.capaPrenda.NivelDeCapa;

public class TestNiveldeCapa {

	@Test
	public  void ordinalNivelCapa(){
		
		Assert.assertEquals(NivelDeCapa.MEDIO.ordinal(),1);

	}
}

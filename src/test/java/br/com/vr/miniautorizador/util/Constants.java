package br.com.vr.miniautorizador.util;

import br.com.vr.miniautorizador.database.entity.CartaoEntity;
import br.com.vr.miniautorizador.rest.dto.CartaoDto;

import static java.math.BigDecimal.TEN;

public class Constants {

    public static final String NUMERO_CARTAO = "6549873025634501";
    public static final String SENHA = "1234";
    public static final CartaoDto CARTAO = new CartaoDto(NUMERO_CARTAO, SENHA);
    public static final CartaoEntity ENTITY = new CartaoEntity(null, NUMERO_CARTAO, SENHA, TEN);
    public static final CartaoEntity ENTITY_COM_ID = new CartaoEntity(1L, NUMERO_CARTAO, SENHA, TEN);
}

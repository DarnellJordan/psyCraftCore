package de.psyCraft.Core.util.refelct;

import java.lang.reflect.Constructor;
import java.util.stream.IntStream;

/**
 * @author psyGamer
 */
public class ReflactionUtil {
	
	public static final Object newInstance(final Class<?> clazz, final Object... constructorParameters) throws Exception {
		final Class[] constructorParameterTypes = new Class[constructorParameters.length];
		
		IntStream.range(0, constructorParameterTypes.length)
				.forEach(index -> constructorParameterTypes[index] = constructorParameters[index].getClass());
		
		final Constructor constructor = clazz.getConstructor(constructorParameterTypes);
		constructor.setAccessible(true);
		return constructor.newInstance(constructorParameters);
	}
}

package de.psyCraft.Core.util.common;

public class Pair <F, S> {
	
	public final F first;
	public final S second;
	
	public Pair(F first, S second) {
		this.second = second;
		this.first = first;
	}
	
	public static <T, U> Pair<T, U> of(T first, U second) {
		return new Pair<>(first, second);
	}
	
	@Override
	public String toString() {
		return "(" + first + ", " + second + ")";
	}
}

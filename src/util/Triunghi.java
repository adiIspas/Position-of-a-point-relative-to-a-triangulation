package util;

import java.util.ArrayList;
import java.util.Collections;

public class Triunghi {
	public Punct a,b,c;
	public Triunghi(){
		this.a = new Punct();
		this.b = new Punct();
		this.c = new Punct();
	}
	public Triunghi(Punct a, Punct b, Punct c){
		this.a = new Punct(a);
		this.b = new Punct(b);
		this.c = new Punct(c);
	}
	public Triunghi(Triunghi t){
		this.a = new Punct(t.a);
		this.b = new Punct(t.b);
		this.c = new Punct(t.c);
	}
	public static double determinant(Punct A, Punct B, Punct C){
		return (A.x-C.x)*(B.y-C.y) - (B.x - C.x)*(A.y - C.y);
	}
	public static double getArie(Punct A, Punct B, Punct C){
		double rez = determinant(A, B, C);
	    return 1.0/2.0*(Math.abs(rez));
	}
	private static boolean equals(double _a, double _b){
		if(Math.abs(_b-_a) > 0.0000000000001)
			return false;
		return true;
	}
	public PunctFataDeTriunghi contains(Punct p){
		double _total, _arie1, _arie2, _arie3;
		_total = Triunghi.getArie(this.a, this.b, this.c);
		_arie1 = Triunghi.getArie(this.a, this.b, p);
		_arie2 = Triunghi.getArie(this.a, this.c, p);
		_arie3 = Triunghi.getArie(this.b, this.c, p);
		if(equals(_total, _arie1+_arie2+_arie3)){
			if(Segment.contains(new Segment(a,b), p))
				return PunctFataDeTriunghi.LATURA_AB;
			if(Segment.contains(new Segment(a,c), p))
				return PunctFataDeTriunghi.LATURA_AC;
			if(Segment.contains(new Segment(b,c), p))
				return PunctFataDeTriunghi.LATURA_BC;
			return PunctFataDeTriunghi.INTERIOR;
		}
		return PunctFataDeTriunghi.EXTERIOR;
	}
	@Override
	public boolean equals(Object t){
		Triunghi _triunghi = (Triunghi) t;
		ArrayList<Punct> _puncteThis = new ArrayList<>(),
				_puncteObject = new ArrayList<>();
		_puncteThis.add(a);
		_puncteThis.add(b);
		_puncteThis.add(c);
		Collections.sort(_puncteThis);
		_puncteObject.add(_triunghi.a);
		_puncteObject.add(_triunghi.b);
		_puncteObject.add(_triunghi.c);
		Collections.sort(_puncteObject);
		if(_puncteThis.containsAll(_puncteObject) && _puncteObject.containsAll(_puncteThis))
			return true;
		return false;
	}
	@Override
	public String toString(){
		return "[" + a + " " + " " + b + " " + c + "]";
	}
	void rotateZ(double theta){
		this.a.rotateZ(theta);
		this.b.rotateZ(theta);
		this.c.rotateZ(theta);
	}
}

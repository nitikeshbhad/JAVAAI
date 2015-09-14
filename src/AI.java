public class AI {
	static Float[] p = { 0f, 1f, 0f, 0f, 0f };
	static String[] world = { "green", "red", "red", "green", "green" };
	static String[] measurements = { "red", "green" };
	static float pHit = 0.6f;
	static float pMiss = 0.2f;
	private static Float pExact = 0.8f;
	private static Float pOvershoot = 0.1f;
	private static Float pUndershoot = 0.1f;

	static Float[] sense(Float[] p, String Z) {

		Float[] q = new Float[p.length];
		for (int i = 0; i < q.length; i++) {
			q[i] = 0.0f;
		}
		for (int i = 0; i < p.length; i++) {
			int hit = Z.equals(world[i]) ? 1 : 0;
			q[i] += p[i] * (hit * pHit + (1 - hit) * pMiss);
		}
		float s = 0;
		for (int i = 0; i < q.length; i++) {
			s += q[i];
		}
		for (int i = 0; i < p.length; i++) {
			q[i] = q[i] / s;
		}
		return q;
	}

	static Float[] move(Float[] p, int U) {

		Float[] q = new Float[p.length];
		for (int i = 0; i < q.length; i++) {
			q[i] = 0.0f;
		}
		for (int i = 0; i < q.length; i++) {
			float s = pExact
					* p[i - U < 0 ? p.length -1- (Math.abs(i - U) % p.length)
							: Math.abs(i - U) % p.length];
			s += pOvershoot
					* p[i - U < 0 ? p.length - 1 - (Math.abs(i - U - 1) % p.length)
							: Math.abs(i - U - 1) % p.length];
			s += pUndershoot
					* p[i - U < 0 ? p.length -1- (Math.abs(i - U + 1) % p.length)
							: Math.abs(i - U + 1) % p.length];
			q[i] += s;
		}
		return q;
	}

	public static void main(String[] args) {
		// for (int i = 0; i < measurements.length; i++) {
		// p = sense(p, measurements[i]);
		//
		// }
		
		for (int j = 0; j < 1000; j++) {
			p = move(p, 1);
		}
		for (int j = 0; j < p.length; j++) {
			System.out.println(p[j]);
		}

	}
}

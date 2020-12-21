package pack7_JFrame;

abstract class RungeKutta{ // oルンゲクッタ法
	double H;     // o刻み幅
	double N;
	double x, dx, x2, dx2, t;
	double x0, dx0, t0;

	// o刻み幅，初期振幅，初速度，開始時刻の初期化
	public RungeKutta(double H, double x0, double dx0, double t0){
		this.H   = H;
		this.x0  = x0;
		this.dx0 = dx0;
		this.t0  = t0;
	}
	abstract double f(double t, double x, double dx);

	public void init(){
		x  = x0;
		dx = dx0;
		t  = t0;
	}

	public void runge(){
		double k[];
		double v[];

		k = new double[4];
		v = new double[4];

		x2 = x;
		dx2 = dx;

		k[0] = H * dx;
		v[0] = H * f(t, x2, dx2);
		k[1] = H * (dx2 + v[0]/2);
		v[1] = H * f(t + H/2, x2 + k[0]/2, dx2 + v[0]/2);
		k[2] = H * (dx2 + v[1]/2);
		v[2] = H * f(t + H/2, x2 + k[1]/2, dx2 + v[1]/2);
		k[3] = H * (dx2 + v[2]);
		v[3] = H * f(t + H, x2 + k[2], dx2 + v[2]);

		x  = x2  + (k[0] + 2 * k[1] + 2 * k[2] + k[3])/6;
		dx = dx2 + (v[0] + 2 * v[1] + 2 * v[2] + v[3])/6;

		t = t + H;
	}
}

class Spring extends RungeKutta { //oバネのシミュレーションプログラム
	double K = 1.0; //oばね定数
	double L = 100; //oばねの長さ
	double W = 40;  //oばねの幅
	int N = 30;     //oばねの分割点の数
	int n_x[];      //oばねの分割点のx座標
	int n_y[];      //oばねの分割点のy座標

	public Spring() {
		super(0.1, 10, 40, 0); //o刻み幅，初期振幅，初速度，開始時刻の初期化
		init();
	}

	public double f(double t, double x, double dx) {
	  return - K * x;
	}

	//oばねの分割点の場所を計算する
	public void setPoint() {
		n_x = new int[N];
		n_y = new int[N];
		n_x[0] = 0;
		n_x[N - 1] = (int)(L + x);
		n_y[0] = n_y[N - 1] = 0;
		for(int i = 1; i < N - 1; i++) {
			n_x[i] = (int)((i + 1)*(L + x)/(N + 1));
			n_y[i] = (int)(Math.pow(-1, i) * W);
		}
	}
}
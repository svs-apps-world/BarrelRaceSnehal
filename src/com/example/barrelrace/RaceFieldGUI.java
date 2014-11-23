package com.example.barrelrace;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.Toast;

public class RaceFieldGUI extends ImageView implements AccelerometerListener{
	
	private Context mContext;
	int x = -1;
	int y = -1;
	//private int xVelocity = 10;
	//private int yVelocity = 5;
	private float  mSensorX ; 
	private float  mSensorZ;
	private Handler handler;
	//private Horse mBall = new Horse();
	private Bitmap mBitmap;
    public float mPosX;
    public float mPosY;
    final float alpha = (float) 0.8;
    private int screen_width = getWidth();
	private int screen_height = getHeight();
	private float xVelocity = 10;
	private float yVelocity = 5;
	

	
	public RaceFieldGUI(Context context, AttributeSet attrs)  {  
		super(context, attrs);  
		mContext = context;  
		handler = new Handler();
		
		Bitmap ball = BitmapFactory.decodeResource(getResources(), R.drawable.ball);
        mBitmap = Bitmap.createScaledBitmap(ball, 32,32, true);
        
      
        
    } 
	
	private Runnable r = new Runnable() {
		@Override
		public void run() {
			invalidate(); 
		}
	};
	
	public void onAccelerationChanged(float x, float y, float z, long ts) {
		mSensorX = x;
		mSensorZ = z;
		Toast.makeText(mContext, "Values:" + mPosX + " " + mPosY + " " + mSensorZ, 
				Toast.LENGTH_SHORT ).show();

		//updateBall();
	
	}
	
//	public void updateBall(){
//		float frameTime = 0.666f;
//		
//		//Calculate new speed
//	    xVelocity += (mSensorX * frameTime);
//	    yVelocity += (mSensorY * frameTime);
//
//	    //Calc distance travelled in that time
//	    float xS = (xVelocity/2)*frameTime;
//	    float yS = (yVelocity/2)*frameTime;
//
//	    //Add to position negative due to sensor 
//	    //readings being opposite to what we want!
//	    mPosX -= xS; 
//	    mPosY -= yS;
//
//	    if (mPosX > screen_width) {
//	    	mPosX = screen_width;
//	    } else if (mPosX < 0) {
//	    	mPosX = 0;
//	    }
//	    if (mPosY > screen_height) { 
//	    	mPosY = screen_height;
//	    } else if (mPosY < 0) {
//	    	mPosY = 0;
//	    }
//	}

	public void onShake(float force) {
		
		// Called when Motion Detected
		Toast.makeText(mContext, "Motion detected", 
				Toast.LENGTH_LONG).show();
		
	}
	
	protected void onDraw(Canvas c) {  
		
		screen_width = getWidth();
		screen_height = getHeight();
		
		BitmapDrawable grass = (BitmapDrawable) mContext.getResources().getDrawable(R.drawable.grass_note2);  
		BitmapDrawable barrel_1 = (BitmapDrawable) mContext.getResources().getDrawable(R.drawable.barrel_onground2);  
		BitmapDrawable barrel_2 = (BitmapDrawable) mContext.getResources().getDrawable(R.drawable.barrel_onground2);  
		BitmapDrawable barrel_3 = (BitmapDrawable) mContext.getResources().getDrawable(R.drawable.barrel_onground2);
		BitmapDrawable notice_board = (BitmapDrawable) mContext.getResources().getDrawable(R.drawable.wood);
	    
		BitmapDrawable ball = (BitmapDrawable) mContext.getResources().getDrawable(R.drawable.obj_horse);  
	    
		
		 //Check device supported Accelerometer sensor or not
        if (AccelerometerManager.isSupported(mContext)) {
        	
        	//Start Accelerometer Listening
			AccelerometerManager.startListening(this);
        }
		if (x<0 && y <0) {
	    	x = this.getWidth()/2;
	    	y = this.getHeight()/2;
	    } else {
	    	x += xVelocity;
	    	y += yVelocity;
	    	if ((x > this.getWidth() - ball.getBitmap().getWidth()) || (x < 0)) {
	    		xVelocity = xVelocity*-1;
	    	}
	    	if ((y > this.getHeight() - ball.getBitmap().getHeight()) || (y < 0)) {
	    		yVelocity = yVelocity*-1;
	    	}
	    }
	    
	    //Get Barrel's HEIGHT and WIDTH being displayed.
	    int barrel_width =  barrel_1.getMinimumWidth();
	    int barrel_height = barrel_1.getMinimumHeight();
        //Set position of all barrels. 
	    //Barrel 1 LEFT and TOP position
	    int barrel1_left = screen_width/2 - (barrel_width/2);
	    int barrel1_top = screen_height/2 - (barrel_height/2); //((screen_height/2)/2);
	    //Barrel 2 LEFT and TOP position
	    int barrel2_left = (screen_width/2 - ((screen_width/2)/2)) - (barrel_width/2);
	    int barrel2_top = (screen_height/2 + ((screen_height/2)/2)) - (barrel_height/2);
	    //Barrel 3 LEFT and TOP position
	    int barrel3_left = (screen_width/2 + ((screen_width/2)/2))- (barrel_width/2);
	    int barrel3_top = (screen_height/2 + ((screen_height/2)/2))- (barrel_height/2);
	    
	    //DRAW all the BITMAP images on screen using CANVAS c..
	    c.drawBitmap(notice_board.getBitmap(), 0, 0, null);
	    c.drawBitmap(grass.getBitmap(), 0, ((screen_height/2)/2), null);
	    c.drawBitmap(barrel_1.getBitmap(), barrel1_left, barrel1_top, null);
	    c.drawBitmap(barrel_2.getBitmap(), barrel2_left, barrel2_top, null);
	    c.drawBitmap(barrel_3.getBitmap(), barrel3_left, barrel3_top, null);
	    //--------------------------------------------------------------------------
	    // POSITIONING BALL
//	    float ball_x =  mSensorX * 100;
//	    float ball_y =  mSensorY * 100;
//	    if (ball_x<0 && y <0) {
//	    	ball_x = this.getWidth()/2;
//	    	ball_y = this.getHeight()/2;
//	    } else {
//	    	//ball_x += xVelocity;
//	    	//ball_y += yVelocity;
//	    	if ((ball_x > this.getWidth() - ball.getBitmap().getWidth()) || (ball_x < 0)) {
//	    		//xVelocity = xVelocity*-1;
//	    		ball_x = screen_width;
//	    	}
//	    	if ((ball_y > this.getHeight() - ball.getBitmap().getHeight()) || (ball_y < 0)) {
//	    		//yVelocity = yVelocity*-1;
//	    		ball_y = screen_height;
//	    	}
//	    }
//	    c.drawBitmap(ball.getBitmap(), ball_x , ball_y, null);  
//	    //ball.updatePosition(mSensorX, mSensorY, mSensorZ, mSensorTimeStamp);
//	    mBall.updatePosition( mSensorX,  mSensorY, screen_z, mSensorTimeStamp);
//	    c.drawBitmap(mBitmap, (screen_width - 16) + mBall.mPosX, (screen_height - 16) - mBall.mPosY, null);
	      
	    
//	    float dt = (System.nanoTime() - mSensorTimeStamp) / 1000000000.0f;
//    	mVelX += - mSensorX * dt;
//    	mVelY += - mSensorY * dt;
//    	
//    	mPosX += mVelX * dt;
//    	mPosY += mVelY * dt;
    	
//    	mPosX = screen_width/2;
//    	mPosY = screen_height/2;
    	
    	// In this example, alpha is calculated as t / (t + dT),
    	  // where t is the low-pass filter's time-constant and
    	  // dT is the event delivery rate.

    	  
//    	  // Isolate the force of gravity with the low-pass filter.
//    	  gravityX = alpha * gravityX + (1 - alpha) * mSensorX;
//    	  gravityY = alpha * gravityY + (1 - alpha) * mSensorY;
//    	  gravityZ = alpha * gravityZ + (1 - alpha) * mSensorZ;
//
//    	  // Remove the gravity contribution with the higmSensorXh-pass filter.
//    	  mPosX = mSensorX - gravityX;
//    	  mPosY = mSensorY - gravityY;
//    	  //linear_acceleration[2] = mSensorZ - gravity[2];
//    	 mPosX = mPosX * 1000;
//    	 mPosY = mPosY * 1000;
//    	c.drawBitmap(ball.getBitmap(), mPosX , mPosY, null);  
//    	
//    	if (mPosX > screen_width) {
//            mPosX = screen_width;
//            mVelX = -mVelX * COR;
//        } else if (mPosX < -screen_width) {
//            mPosX = -screen_width;
//            mVelX = -mVelX * COR;
//        }
//        if (mPosY > screen_height) {
//            mPosY = screen_height;
//            mVelY = -mVelY * COR;
//        } else if (mPosY < -screen_height) {
//            mPosY = -screen_height;
//            mVelY = -mVelY * COR;
//        }
	    //--------------------------------------------------------------------------
	    //c.drawBitmap(ball.getBitmap(), mPosX , mPosY, null);  
	    c.drawBitmap(ball.getBitmap(), x , y, null);
//	    
	    Toast.makeText(mContext, "Values:" + mSensorX + " " + mPosY + " " + mSensorZ, 
	    				Toast.LENGTH_SHORT ).show();
	    		
	    //c.drawBitmap(ball.getBitmap(), mPosX, mPosY, null);
        //invalidate();
        
//	    handler.postDelayed(r, FRAME_RATE);
		
	    
	    	      
	} 
}

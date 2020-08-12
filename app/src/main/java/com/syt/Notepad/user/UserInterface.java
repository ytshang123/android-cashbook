package com.syt.Notepad.user;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.syt.Notepad.R;
import com.syt.Notepad.adapter.ViewPagerAdapter;
import com.syt.Notepad.fg.FgAccount;
import com.syt.Notepad.fg.FgBill;
import com.syt.Notepad.fg.FgNoteBook;
import com.syt.Notepad.fg.FgPerson;

import java.util.ArrayList;
import java.util.List;

public class UserInterface extends FragmentActivity {


	// viewpager相关成员变量
	private ViewPager viewpager;
	private ViewPagerAdapter viewadapter;
	private List<Fragment> list;
	private FragmentManager fm;
	private FgAccount faccount;
	private FgBill fbill;
	private FgNoteBook fnotebook;
	private FgPerson fperson;

	 // userinfo-bottom相关成员变量
	private View xian_account,xian_bill,xian_note,xian_person;
	private RelativeLayout notebook_layout,bill_layout,account_layout,person_layout;
	private TextView text_notebook,text_account,text_bill,text_person;


	private MypagerLister pagerlister;
	private MyReLayoutLister relayoutlister;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.user_interface);
		initView();
		init();
		initState();

	}

	// 实例化userinfo

	private void initView(){
		viewpager=(ViewPager)findViewById(R.id.viewpager);
		notebook_layout=(RelativeLayout)findViewById(R.id.notebook_layout);
		bill_layout=(RelativeLayout)findViewById(R.id.bill_layout);
		account_layout=(RelativeLayout)findViewById(R.id.account_layout);
        person_layout = (RelativeLayout) findViewById(R.id.person_layout);
		text_notebook=(TextView)findViewById(R.id.text_notebook);
		text_account=(TextView)findViewById(R.id.text_account);
		text_bill=(TextView)findViewById(R.id.text_bill);
        text_person = (TextView) findViewById(R.id.text_person);
		xian_account=findViewById(R.id.xian_account);
		xian_bill=findViewById(R.id.xian_bill);
		xian_note=findViewById(R.id.xian_note);
        xian_person=findViewById(R.id.xian_person);

		pagerlister=new MypagerLister();
		viewpager.setOnPageChangeListener(pagerlister);
		relayoutlister=new MyReLayoutLister();
		notebook_layout.setOnClickListener(relayoutlister);
		bill_layout.setOnClickListener(relayoutlister);
		account_layout.setOnClickListener(relayoutlister);
        person_layout.setOnClickListener(relayoutlister);

	}

	 //  实例化fragment

	private void init(){
		fm=getSupportFragmentManager();
		faccount=new FgAccount();
		fbill=new FgBill();
		fnotebook=new FgNoteBook();
		fperson = new FgPerson();
		list=new ArrayList<Fragment>();
		list.add(fnotebook);
		list.add(fbill);
		list.add(faccount);
		list.add(fperson);
		viewadapter=new ViewPagerAdapter(fm, list);
		viewpager.setAdapter(viewadapter);
	}

	private void initState(){
		text_notebook.setTextColor(text_notebook.getResources().getColor(R.color.black));
		xian_note.setBackgroundColor(xian_note.getResources().getColor(R.color.blues));
		viewpager.setCurrentItem(0);
	}


	private class MypagerLister implements OnPageChangeListener{

		@Override
		public void onPageScrollStateChanged(int arg0) {
			// TODO Auto-generated method stub
			if(arg0==2){
				int resourceAndVnum=viewpager.getCurrentItem();
				clearChoicked();
				changechoicked(resourceAndVnum);
			}
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onPageSelected(int arg0) {
			// TODO Auto-generated method stub

		}

	}


	private  class MyReLayoutLister implements OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			clearChoicked();
			changechoicked(v.getId());
		}

	}


	private  void clearChoicked(){
		text_notebook.setTextColor(text_notebook.getResources().getColor(R.color.black));
		text_bill.setTextColor(text_bill.getResources().getColor(R.color.black));
		text_account.setTextColor(text_account.getResources().getColor(R.color.black));
        text_person.setTextColor(text_person.getResources().getColor(R.color.black));
		xian_note.setBackgroundColor(xian_note.getResources().getColor(R.color.btom_line));
		xian_bill.setBackgroundColor(xian_bill.getResources().getColor(R.color.btom_line));
		xian_account.setBackgroundColor(xian_account.getResources().getColor(R.color.btom_line));
        xian_person.setBackgroundColor(xian_person.getResources().getColor(R.color.btom_line));
	}

	public void changechoicked(int resourceAndVnum) {

		switch (resourceAndVnum) {
			case R.id.notebook_layout:case 0:
				text_notebook.setTextColor(text_notebook.getResources().getColor(R.color.blues));
				xian_note.setBackgroundColor(xian_note.getResources().getColor(R.color.blues));
				viewpager.setCurrentItem(0);
				break;
			case R.id.bill_layout:case 1:
				text_bill.setTextColor(text_bill.getResources().getColor(R.color.blues));
				xian_bill.setBackgroundColor(xian_note.getResources().getColor(R.color.blues));
				viewpager.setCurrentItem(1);
				break;
			case R.id.account_layout:case 2:
				text_account.setTextColor(text_account.getResources().getColor(R.color.blues));
				xian_account.setBackgroundColor(xian_note.getResources().getColor(R.color.blues));
				viewpager.setCurrentItem(2);
				break;
            case R.id.person_layout:case 3:
                text_person.setTextColor(text_person.getResources().getColor(R.color.blues));
                xian_person.setBackgroundColor(xian_note.getResources().getColor(R.color.blues));
                viewpager.setCurrentItem(3);
                break;
			default:
				break;
		}
	}




}

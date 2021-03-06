/*
 *PopBell Application for Android
 *Copyright (C) 2013 SimpleMinds Team
 *
 *This program is free software; you can redistribute it and/or
 *modify it under the terms of the GNU General Public License
 *as published by the Free Software Foundation; either version 2
 *of the License, or (at your option) any later version.
 *
 *This program is distributed in the hope that it will be useful,
 *but WITHOUT ANY WARRANTY; without even the implied warranty of
 *MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *GNU General Public License for more details.
 *
 *You should have received a copy of the GNU General Public License
 *along with this program; if not, write to the Free Software
 *Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package com.simpleminds.popbell;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;

import com.fima.cardsui.views.CardUI;

public class Appinfo_Notices extends ActionBarActivity {

	private CardUI mCardView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_appinfo_notices);

		mCardView = (CardUI) findViewById(R.id.cardsview);
		MyPlayCard readme = new MyPlayCard(getString(R.string.readme), getString(R.string.subreadme), "#FFFFFF", "#000000", false, true);
		readme.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Appinfo_Notices.this, Doc_Parsing.class);
				intent.putExtra("Data", getString(R.string.readme));
				startActivity(intent);
			}
		});
		mCardView.addCard(readme);

		MyPlayCard notices = new MyPlayCard(getString(R.string.notices), getString(R.string.subnotice), "#FFFFFF", "#000000", false, true);
		notices.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Appinfo_Notices.this, Doc_Parsing.class);
				intent.putExtra("Data", getString(R.string.notices));
				startActivity(intent);
			}
		});
		mCardView.addCard(notices);

		MyPlayCard copying = new MyPlayCard(getString(R.string.copying), getString(R.string.subcopyng), "#FFFFFF", "#000000", false, true);
		copying.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Appinfo_Notices.this, Doc_Parsing.class);
				intent.putExtra("Data", getString(R.string.copying));
				startActivity(intent);
			}
		});
		mCardView.addCard(copying);

		MyPlayCard contributors = new MyPlayCard(getString(R.string.contributors), getString(R.string.subcontributors), "#FFFFFF", "#000000", false, true);
		contributors.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Appinfo_Notices.this, Doc_Parsing.class);
				intent.putExtra("Data", getString(R.string.contributors));
				startActivity(intent);
			}
		});
		mCardView.addCard(contributors);
		// draw cards
		mCardView.refresh();
	}
}
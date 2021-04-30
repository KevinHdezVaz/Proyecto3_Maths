package com.vazk.ncalc.document;

import android.os.Bundle;
import androidx.annotation.Nullable;
import com.vazk.calculator.R;

import com.vazk.calculator.activities.base.BaseActivity;

public class MarkdownListDocumentActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_with_fragment_content);
        setupToolbar();
        setTitle(R.string.documentation);
        MarkdownListDocumentFragment fragment = MarkdownListDocumentFragment.newInstance();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content, fragment).commitAllowingStateLoss();
    }
}

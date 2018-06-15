package com.projects.android.popularmoviesstage1.Utils;

import android.util.Log;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.IOException;

import static junit.framework.Assert.assertEquals;

/**
 * Created by alshtray on 6/14/18.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(Log.class)
public class ApiKeyReaderTest {

    @Before
    public void setUp(){
        PowerMockito.mockStatic(Log.class);
    }
    @Test
    public void givenValidKeyPathWhenReadApiKeyIsCalledThenCorrectApiKeyIsRead() throws IOException {
        String key = ApiKeyReader.readApiKey(this,"mock_api_key.txt");
        assertEquals("5up3rs3cretk3y5111", key);
    }
}

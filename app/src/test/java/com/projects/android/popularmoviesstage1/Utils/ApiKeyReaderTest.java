package com.projects.android.popularmoviesstage1.Utils;

import android.util.Log;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
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

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp(){
        PowerMockito.mockStatic(Log.class);
    }

    @Test
    public void givenValidKeyPathWhenReadApiKeyIsCalledThenCorrectApiKeyIsRead() throws IOException {
        String key = ApiKeyReader.readApiKey(this,"mock_api_key.txt");
        assertEquals("5up3rs3cretk3y5111", key);
    }

    @Test
    public void givenInvalidKeyPathWhenReadApiKeyIsCalledThenIOExceptionsIsThrown() throws  IOException{
        //got help from following stackoverflow:
        //https://stackoverflow.com/questions/7552587/how-to-test-assert-throws-exception-in-android
        exception.expect(IOException.class);
        String key = ApiKeyReader.readApiKey(this,"");
    }
}

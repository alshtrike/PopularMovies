package com.projects.android.popularmoviesstage1.Utils;

import org.junit.Test;

import java.io.IOException;

import static junit.framework.Assert.assertEquals;

/**
 * Created by alshtray on 6/14/18.
 */
public class ApiKeyReaderTest {

    @Test
    public void givenValidKeyPathWhenReadApiKeyIsCalledThenCorrectApiKeyIsRead() throws IOException {
        String key = ApiKeyReader.readApiKey("mock_api_key.txt");
        assertEquals("5up3rs3cretk3y5111", key);
    }
}

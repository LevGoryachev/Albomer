package ru.goryachev.albomer;

import org.junit.Assert;
import org.junit.Test;

public class EditorTest {

    public String savedName;
    public String savedDesc;
    public String savedField;

    @Test
    public void testEditor() {
        Editor starter = new Editor(savedName, savedDesc, savedField);
        Assert.assertNotNull(starter);
    }
}
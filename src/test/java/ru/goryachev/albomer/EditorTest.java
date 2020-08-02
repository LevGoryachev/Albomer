package ru.goryachev.albomer;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class EditorTest {

    public String savedName;
    public String savedDesc;
    public String savedField;

    @Test
    public void testEditor() {
        Editor editor = new Editor(savedName, savedDesc, savedField);

        List<String> expected = new ArrayList<>();
        expected.add(savedName);
        expected.add(savedDesc);
        expected.add(savedField);

        List<String> actual = new ArrayList<>();
        actual.add(editor.getName());
        actual.add(editor.getDesc());
        actual.add(editor.getTxtInField());

        Assert.assertNotNull(editor);
        Assert.assertEquals(expected, actual);

    }
}
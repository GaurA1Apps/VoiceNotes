package com.app.voicenotesai.utils

object Constants {

    const val TABLE_NAME = "records"

    const val PROMPT =
        "Please transcribe the speech in this audio file into text. " +
                "Then, Generate the content on basis of the transcript. And list as much information as you know about the topic and generate bullet points if possible" +
                "Also, create a concise and descriptive 4-5 word title based on the content. " +
                "Ensure the result follows this format exactly:\n" +
                "Title: [Provide a concise and meaningful title here]\n" +
                "Summary: [Content here]\n" +
                "Transcript: [Transcript here]"
}
package com.xenous.storyline.data

data class Story(
    val author : String,
    val name : String,
    val path_to_text: String,
    val time_to_read: Long,
    val genres: List<Long>
) {
    override fun toString(): String {
        return "Story's author is $author. Story's name is $name. Path to story.html's text in the storage is $path_to_text. Time to read of this story.html is $time_to_read"
    }
}
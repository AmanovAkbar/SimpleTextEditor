commands:
A "lastword"
Adds symbols to the tail
a "firstword"
adds symbols to the head
D 10
deletes from the tail
d 10
deletes from the head
m 2 add
defines macros based on two last operations //not compatible with spell-m
$add
calls for a defined macros
l 10
lists last operations //not compatible with spell-m
lang eng
defines language of spellcheck as english //loads given dictionary
lang fra
defines language of spellcheck as french //loads given dictionary
content txt
defines content type as txt
content xml
defines content type as xml
close
creates file with given text, closes the program.
spell
performs a spell check using given dictionary and Aho-Corasick algorithm, prints words that didn't pass the spellcheck
spell-a
performs a spell check using given dictionary and Aho-Corasick algorithm, prints the whole string but highlights the words that didn't pass the spellcheck
spell-m
deletes words that didn't pass the spell check. Can't be undone or added to macros, because I made a poor design of Operation class
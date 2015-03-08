# jSmite
## Java wrapper for the Hi-Rez Smite API


```
public static void main(String... args) {
	// Create a Smite object with your developer ID and auth key provided by Hi-Rez
    Smite smite = new Smite("1132", "29FA3BCE7ACF4BDDA7310A352FB855BE");
    
    // By default jSmite returns all data as JSON, but if you want, you can set it to return xml
    smite.setResponseFormat(Smite.RESPONSE_TYPE_XML);
    
    // Some methods require some extra parameters
    String gods = smite.getGods(Smite.LanguageCode.ENGLISH);
    String player = smite.getPlayer("playerName");
}
```

### Dependencies
* [OkHttp](https://github.com/square/okhttp)/[Okio](https://github.com/square/okio) - Used to make network calls
* [minimal-json](https://github.com/ralfstx/minimal-json) - Used to process server response when creating a new API session

### License
The MIT License (MIT)

Copyright (c) 2015 Ben Butzow

Permission is hereby granted, free of charge, to any person obtaining a copy of
this software and associated documentation files (the "Software"), to deal in
the Software without restriction, including without limitation the rights to
use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
the Software, and to permit persons to whom the Software is furnished to do so,
subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
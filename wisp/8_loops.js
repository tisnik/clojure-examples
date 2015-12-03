(function loop() {
    var recur = loop;
    var iø1 = 0;
    do {
        recur = iø1 == 10 ? sum : (loop[0] = iø1 + 1, loop);
    } while (iø1 = loop[0], recur === loop);
    return recur;
}.call(this));
(function loop() {
    var recur = loop;
    var iø1 = 0;
    var sumø1 = 1;
    do {
        recur = iø1 == 10 ? sumø1 : (loop[0] = iø1 + 1, loop[1] = sumø1 * 2, loop);
    } while (iø1 = loop[0], sumø1 = loop[1], recur === loop);
    return recur;
}.call(this));
var pow2 = exports.pow2 = function pow2(n) {
    return function loop() {
        var recur = loop;
        var iø1 = 0;
        var sumø1 = 1;
        do {
            recur = iø1 == n ? sumø1 : (loop[0] = iø1 + 1, loop[1] = sumø1 * 2, loop);
        } while (iø1 = loop[0], sumø1 = loop[1], recur === loop);
        return recur;
    }.call(this);
};
var array = exports.array = [
    1,
    2,
    3,
    4,
    5
];
(function loop() {
    var recur = loop;
    var iø1 = 0;
    var sumø1 = 0;
    do {
        recur = iø1 == 5 ? sumø1 : (loop[0] = iø1 + 1, loop[1] = sumø1 + (array || 0)[iø1], loop);
    } while (iø1 = loop[0], sumø1 = loop[1], recur === loop);
    return recur;
}.call(this));
//# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbImFub255bW91cy53aXNwIl0sIm5hbWVzIjpbImnDuDEiLCJzdW0iLCJzdW3DuDEiLCJwb3cyIiwiZXhwb3J0cyIsIm4iLCJhcnJheSJdLCJtYXBwaW5ncyI6IkFBaUJBLEM7O0lBQU8sSUFBQUEsRyxHQUFFLENBQUYsQzs7Z0JBQ0tBLEdBQUosSUFBTSxFQUFWLEdBQ0lDLEdBREosR0FFSSxDLFVBQVVELEdBQUgsR0FBSyxDQUFaLEUsSUFBQSxDO2FBSERBLEc7O01BQVAsQyxJQUFBO0FBTUEsQzs7SUFBTyxJQUFBQSxHLEdBQUUsQ0FBRixDO0lBQUksSUFBQUUsSyxHQUFJLENBQUosQzs7Z0JBQ0NGLEdBQUosSUFBTSxFQUFWLEdBQ0lFLEtBREosR0FFSSxDLFVBQVVGLEdBQUgsR0FBSyxDQUFaLEUsVUFBa0JFLEtBQUgsR0FBTyxDQUF0QixFLElBQUEsQzthQUhERixHLFlBQUlFLEs7O01BQVgsQyxJQUFBLEdBTkE7QUFZQSxJQUFNQyxJQUFBLEdBQUFDLE9BQUEsQ0FBQUQsSUFBQSxHQUFOLFNBQU1BLElBQU4sQ0FDS0UsQ0FETCxFQUVJO0FBQUEsVzs7UUFBTyxJQUFBTCxHLEdBQUUsQ0FBRixDO1FBQUksSUFBQUUsSyxHQUFJLENBQUosQzs7b0JBQ0NGLEdBQUosSUFBTUssQ0FBVixHQUNJSCxLQURKLEdBRUksQyxVQUFVRixHQUFILEdBQUssQ0FBWixFLFVBQWtCRSxLQUFILEdBQU8sQ0FBdEIsRSxJQUFBLEM7aUJBSERGLEcsWUFBSUUsSzs7VUFBWCxDLElBQUE7QUFBQSxDQUZKLENBWkE7QUFvQkEsSUFBS0ksS0FBQSxHQUFBRixPQUFBLENBQUFFLEtBQUEsR0FBTTtBQUFBLElBQUMsQ0FBRDtBQUFBLElBQUcsQ0FBSDtBQUFBLElBQUssQ0FBTDtBQUFBLElBQU8sQ0FBUDtBQUFBLElBQVMsQ0FBVDtBQUFBLENBQVgsQ0FwQkE7QUFzQkEsQzs7SUFBTyxJQUFBTixHLEdBQUUsQ0FBRixDO0lBQUksSUFBQUUsSyxHQUFJLENBQUosQzs7Z0JBQ0NGLEdBQUosSUFBTSxDQUFWLEdBQ0lFLEtBREosR0FFSSxDLFVBQVVGLEdBQUgsR0FBSyxDQUFaLEUsVUFBa0JFLEtBQUgsRyxDQUFZSSxLLE1BQUwsQ0FBV04sR0FBWCxDQUF0QixFLElBQUEsQzthQUhEQSxHLFlBQUlFLEs7O01BQVgsQyxJQUFBIiwic291cmNlc0NvbnRlbnQiOlsiOyBFeGFtcGxlcyBmb3IgV2lzcDogSG9tb2ljb25pYyBKUyB3aXRoIGNsb2p1cmUgc3ludGF4LCBzLWV4cHJlc3Npb25zICYgbWFjcm9zXG47XG47ICAoQykgQ29weXJpZ2h0IDIwMTUgIFBhdmVsIFRpc25vdnNreVxuO1xuOyAgQWxsIHJpZ2h0cyByZXNlcnZlZC4gVGhpcyBwcm9ncmFtIGFuZCB0aGUgYWNjb21wYW55aW5nIG1hdGVyaWFsc1xuOyAgYXJlIG1hZGUgYXZhaWxhYmxlIHVuZGVyIHRoZSB0ZXJtcyBvZiB0aGUgRWNsaXBzZSBQdWJsaWMgTGljZW5zZSB2MS4wXG47ICB3aGljaCBhY2NvbXBhbmllcyB0aGlzIGRpc3RyaWJ1dGlvbiwgYW5kIGlzIGF2YWlsYWJsZSBhdFxuOyAgaHR0cDovL3d3dy5lY2xpcHNlLm9yZy9sZWdhbC9lcGwtdjEwLmh0bWxcbjtcbjsgIENvbnRyaWJ1dG9yczpcbjsgwqDCoMKgwqAgUGF2ZWwgVGlzbm92c2t5XG47XG5cbjsgVGVzdCBwxZlla2xhZHUgcHJvZ3JhbW92w71jaCBzbXnEjWVrIGRvIEphdmFTY3JpcHR1XG5cblxuOyBwb8SNaXRhZGxvIG9kIDAgZG8gMTBcbihsb29wIFtpIDBdXG4gICAgKGlmICg9PSBpIDEwKVxuICAgICAgICBzdW1cbiAgICAgICAgKHJlY3VyICgrIGkgMSkpKSlcblxuOyB2w71wb8SNZXQgZGVzw6F0w6kgbW9jbmlueSBkdm9qa3lcbihsb29wIFtpIDAgc3VtIDFdXG4gICAgKGlmICg9PSBpIDEwKVxuICAgICAgICBzdW1cbiAgICAgICAgKHJlY3VyICgrIGkgMSkgKCogc3VtIDIpKSkpXG5cbjsgZnVua2NlIHBybyB2w71wb8SNZXQgbi10w6kgbW9jbmlueSBkdm9qa3lcbihkZWZuIHBvdzJcbiAgICBbbl1cbiAgICAobG9vcCBbaSAwIHN1bSAxXVxuICAgICAgICAoaWYgKD09IGkgbilcbiAgICAgICAgICAgIHN1bVxuICAgICAgICAgICAgKHJlY3VyICgrIGkgMSkgKCogc3VtIDIpKSkpKVxuXG47IHDFmcOta2xhZCBwcm8gdsO9cG/EjWV0IHN1bXkgcG9sZVxuKGRlZiBhcnJheSBbMSAyIDMgNCA1XSlcblxuKGxvb3AgW2kgMCBzdW0gMF1cbiAgICAoaWYgKD09IGkgNSlcbiAgICAgICAgc3VtXG4gICAgICAgIChyZWN1ciAoKyBpIDEpICgrIHN1bSAoZ2V0IGFycmF5IGkpICkpKSlcblxuIl19

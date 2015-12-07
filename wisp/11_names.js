var calculateMultiplication = exports.calculateMultiplication = function calculateMultiplication(x, y) {
    return x * y;
};
var isZero = exports.isZero = function isZero(x) {
    return x == 0;
};
var isEven = exports.isEven = function isEven(x) {
    return isZero(x % 2);
};
var isOdd = exports.isOdd = function isOdd(x) {
    return !isEven(x);
};
var stringToBool = exports.stringToBool = function stringToBool(s) {
    return s == 'true';
};
var degToRad = exports.degToRad = function degToRad(angle) {
    return angle * (3.1415 / 180);
};
var __hidden__ = exports.__hidden__ = function __hidden__(x) {
    return x + 1;
};
//# sourceMappingURL=data:application/json;base64,eyJ2ZXJzaW9uIjozLCJzb3VyY2VzIjpbImFub255bW91cy53aXNwIl0sIm5hbWVzIjpbImNhbGN1bGF0ZU11bHRpcGxpY2F0aW9uIiwiZXhwb3J0cyIsIngiLCJ5IiwiaXNaZXJvIiwiaXNFdmVuIiwiaXNPZGQiLCJzdHJpbmdUb0Jvb2wiLCJzIiwiZGVnVG9SYWQiLCJhbmdsZSIsIl9faGlkZGVuX18iXSwibWFwcGluZ3MiOiJBQWlCQSxJQUFNQSx1QkFBQSxHQUFBQyxPQUFBLENBQUFELHVCQUFBLEdBQU4sU0FBTUEsdUJBQU4sQ0FDS0UsQ0FETCxFQUNPQyxDQURQLEVBRUk7QUFBQSxXQUFHRCxDQUFILEdBQUtDLENBQUw7QUFBQSxDQUZKO0FBTUEsSUFBTUMsTUFBQSxHQUFBSCxPQUFBLENBQUFHLE1BQUEsR0FBTixTQUFNQSxNQUFOLENBQ0tGLENBREwsRUFFSTtBQUFBLFdBQUlBLENBQUosSUFBTSxDQUFOO0FBQUEsQ0FGSixDQU5BO0FBVUEsSUFBTUcsTUFBQSxHQUFBSixPQUFBLENBQUFJLE1BQUEsR0FBTixTQUFNQSxNQUFOLENBQ0tILENBREwsRUFFSTtBQUFBLFdBQUNFLE1BQUQsQ0FBWUYsQ0FBTCxHQUFPLENBQWQ7QUFBQSxDQUZKLENBVkE7QUFjQSxJQUFNSSxLQUFBLEdBQUFMLE9BQUEsQ0FBQUssS0FBQSxHQUFOLFNBQU1BLEtBQU4sQ0FDS0osQ0FETCxFQUVJO0FBQUEsWUFBTUcsTUFBRCxDQUFPSCxDQUFQLENBQUw7QUFBQSxDQUZKLENBZEE7QUFvQkEsSUFBTUssWUFBQSxHQUFBTixPQUFBLENBQUFNLFlBQUEsR0FBTixTQUFNQSxZQUFOLENBQ0tDLENBREwsRUFFSTtBQUFBLFdBQUlBLENBQUosSUFBTSxNQUFOO0FBQUEsQ0FGSixDQXBCQTtBQXdCQSxJQUFNQyxRQUFBLEdBQUFSLE9BQUEsQ0FBQVEsUUFBQSxHQUFOLFNBQU1BLFFBQU4sQ0FDS0MsS0FETCxFQUVJO0FBQUEsV0FBR0EsS0FBSCxHQUFTLENBQUcsTUFBSCxHQUFVLEdBQVYsQ0FBVDtBQUFBLENBRkosQ0F4QkE7QUE4QkEsSUFBTUMsVUFBQSxHQUFBVixPQUFBLENBQUFVLFVBQUEsR0FBTixTQUFNQSxVQUFOLENBQ0tULENBREwsRUFFSTtBQUFBLFdBQUdBLENBQUgsR0FBSyxDQUFMO0FBQUEsQ0FGSiIsInNvdXJjZXNDb250ZW50IjpbIjsgRXhhbXBsZXMgZm9yIFdpc3A6IEhvbW9pY29uaWMgSlMgd2l0aCBjbG9qdXJlIHN5bnRheCwgcy1leHByZXNzaW9ucyAmIG1hY3Jvc1xuO1xuOyAgKEMpIENvcHlyaWdodCAyMDE1ICBQYXZlbCBUaXNub3Zza3lcbjtcbjsgIEFsbCByaWdodHMgcmVzZXJ2ZWQuIFRoaXMgcHJvZ3JhbSBhbmQgdGhlIGFjY29tcGFueWluZyBtYXRlcmlhbHNcbjsgIGFyZSBtYWRlIGF2YWlsYWJsZSB1bmRlciB0aGUgdGVybXMgb2YgdGhlIEVjbGlwc2UgUHVibGljIExpY2Vuc2UgdjEuMFxuOyAgd2hpY2ggYWNjb21wYW5pZXMgdGhpcyBkaXN0cmlidXRpb24sIGFuZCBpcyBhdmFpbGFibGUgYXRcbjsgIGh0dHA6Ly93d3cuZWNsaXBzZS5vcmcvbGVnYWwvZXBsLXYxMC5odG1sXG47XG47ICBDb250cmlidXRvcnM6XG47IMKgwqDCoMKgIFBhdmVsIFRpc25vdnNreVxuO1xuXG47IEptw6luYSBmdW5rY8OtIGdlbmVyb3ZhbsOhIHRyYW5zcMWZZWtsYWRhxI1lbSBXaXNwXG5cblxuOyBCxJvFvm7DoSBmdW5rY2UgemFwaXNvdmFuw6EgdmUgc3R5bHUgTElTUHVcbihkZWZuIGNhbGN1bGF0ZS1tdWx0aXBsaWNhdGlvblxuICAgIFt4IHldXG4gICAgKCogeCB5KSlcblxuXG47IFByZWRpa8OhdHlcbihkZWZuIHplcm8/XG4gICAgW3hdXG4gICAgKD09IHggMCkpXG5cbihkZWZuIGV2ZW4/XG4gICAgW3hdXG4gICAgKHplcm8/IChtb2QgeCAyKSkpXG5cbihkZWZuIG9kZD9cbiAgICBbeF1cbiAgICAobm90IChldmVuPyB4KSkpXG5cblxuOyBLb252ZXJ6bsOtIGZ1bmtjZVxuKGRlZm4gc3RyaW5nLT5ib29sXG4gICAgW3NdXG4gICAgKD09IHMgXCJ0cnVlXCIpKVxuXG4oZGVmbiBkZWctPnJhZFxuICAgIFthbmdsZV1cbiAgICAoKiBhbmdsZSAoLyAzLjE0MTUgMTgwKSkpXG5cblxuOyBQcml2w6F0bsOtIGZ1bmtjZVxuKGRlZm4gKipoaWRkZW4qKlxuICAgIFt4XVxuICAgICgrIHggMSkpXG5cbiJdfQ==

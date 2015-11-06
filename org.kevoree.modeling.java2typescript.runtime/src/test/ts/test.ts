var r = new java.util.Random();
org.junit.Assert.assertEquals(typeof r.nextInt(), 'number');

var t0 = [0,1,2];
var t1 = [8,9];
java.lang.System.arraycopy(t0, 0, t1, 1, t0.length);
org.junit.Assert.assertArrayEquals(t1, [8,0,1,2]);

console.log('All tests ok.');

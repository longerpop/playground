-- quicksort, not in-place, not optimized, just a really tiny implementation
-- example
qs [ ]     = [ ]
qs (x : z) = qs [y | y <- z, y < x] ++ x : qs [ y | y <- z, y >= x ]


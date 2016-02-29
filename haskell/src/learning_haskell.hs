heron a b c = sqrt (s * (s - a) * (s - b) * (s - c))
    where s = (a + b + c) / 2

roots :: Floating a => a -> a -> a -> (a, a)
roots a b c =
    let sdisc = sqrt (b * b - 4 * a * c)
    in ((-b + sdisc) / (2 * a),
        (-b - sdisc) / (2 * a))

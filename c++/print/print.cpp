// Python-like "print" statement implementation in C++.
// The thing works overloading the comma operator, breaking up its standard
// evaluation rules (will work as a function call, no left-to-right order is
// guaranteed anymore).

#include <iostream>

namespace __hidden__
{

class Print
{
    public:
        Print( ) : m_space(false) { }
        ~Print( ) { std::cout << std::endl; }

        template<typename T> Print &operator , (const T &t)
        {
            if (m_space) std::cout << ' ';
            else         m_space = true;

            std::cout << t;
            return *this;
        }

    private:
        bool m_space;
};

}

#define print __hidden__::Print(),

int main(void)
{
    const int a = 3;
    const int b = 5;

    print "test";
    print "the sum of", a, "and", b, "is", a + b;

    return 0;
}

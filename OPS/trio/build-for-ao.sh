#!/bin/bash

if [ -z "$1" ]; then
    echo "Usage: $0 <package-X.Y.tar.gz> [64]" >&2
    exit 1
fi

: ${1?}

set -e

srcpkg=$1
bits=$2

srcdir=`basename $srcpkg .tar.gz`
gunzip -c $srcpkg | tar -xf -

case ${OS_CPU?} in
    Darwin_*)
	xflags="-mmacosx-version-min=10.5 -arch i386 -arch x86_64"
	conf_flags=--disable-dependency-tracking
	;;
    *)
	case "$bits" in
	    64) xflags="-fPIC" ;;
	    *)  xflags="-fpic" ;;
	esac
	;;
esac

# Can't use -Os here; it seems to lead to a core dump with
# "ao run /bin/sh build.sh" in demo/hello on Solaris 9 sparc.
# May be a bug in trio (?) so we just leave off -Os.

cd "$srcdir"
CFLAGS="-g ${bits:+-m$bits} $xflags -DNDEBUG" sh configure
make clean all

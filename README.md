#VRML Editor

##Description
The goal of the activity proposed by our teacher was to create a simple 2D editor to exercise our expertise in VRML _(Virtual Reality Modeling Language)_.

So everything that was drawn in the editor could be converted to VRML and rendered by compatible viewers.

We went a little further however, deciding instead to make a 3D editor, but nothing too advanced.

The result can be seen through some pictures below:

|**VRML Editor: Transparency (sample)**|**Transparency rendered in wireframe mode**|
|------|------|
|![Transparency](http://www.navossoc.com/wp-content/uploads/2013/05/vrml_transparency.png)|![Transparency rendered in wireframe mode](http://www.navossoc.com/wp-content/uploads/2013/05/vrml_wireframe-e1368393527316.png)|

###Supported Nodes/Fields

* Box (Width, Height, Depth)
* Cylinder (Radius, Height)
* Cone (Radius, Height)
* Sphere (Radius)

_Scale, Rotation, Translation fields are common for all nodes._

##Source code
This source was written in Java language using [NetBeans 7.x](https://netbeans.org/) as IDE and the [Libgdx](http://libgdx.badlogicgames.com/) framework to render the scene.

Additional libraries such as [l2fprod-common](http://l2fprod.com/common/) were used to create the property editor.

This code was co-authored in collaboration with my classmate [Guilherme Maganha Moreira](https://github.com/gmmoreira).

##License:
We decided to release our source code under the [MIT License](http://opensource.org/licenses/MIT).

The [Libgdx](http://libgdx.badlogicgames.com/) framework is licensed under the [Apache License, Version 2.0](http://www.apache.org/licenses/LICENSE-2.0) and is included in binary form.

The [l2fprod-common](http://l2fprod.com/common/) is also licensed under [Apache License, Version 2.0](http://www.apache.org/licenses/LICENSE-2.0) and is included in binary and source form with a few modifications.

The [jcalendar](http://www.toedter.com/en/jcalendar/) and [nachocalendar](http://nachocalendar.sourceforge.net/) are dependencies of [l2fprod-common](http://l2fprod.com/common/) and are licensed under the [LGPLv2.1](http://www.gnu.org/licenses/lgpl-2.1.html) and are include in binary form.

For more details, you can find the readme files and a copy of the licenses in the repository.

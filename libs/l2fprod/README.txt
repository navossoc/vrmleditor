L2FProd.com Common Components 7.3
http://common.L2FProd.com

Swing has lot of components built-in but still some are missing. This
project provides the developer community with these missing
components, components inspired (copied?!) from modern user
interfaces.

------- The Components

the JButtonBar - it is a bar made of buttons [sic], you have seen it
in Mozilla Firebird, IntelliJ.

the JOutlookBar - as seen in Outlook, it stacks components together
and allows only one of the stack to be visible at a given time. The
component extends the JTabbedPane, no surprise regarding its API.

the JTaskPane and JTaskPaneGroup - lot of recent applications bring
contextual item lists from which you can pick tasks related to the
current selection or context. The JTaskPane and JTaskPaneGroup deliver
this feature to java applications.

the JFontChooser and JDirectoryChooser - surprisingly Swing has no
font chooser and using the original JFileChooser to select a directory
is kind of not so user friendly...well, you know how it works.
JFontChooser and JDirectoryChooser address these two issues.

the PropertySheet - it puts together a list of properties and their
editors. Each property is given a name, a type, a description.It also
supports JavaBeans through BeanInfos and PropertyDescriptors.

the JTipOfTheDay - it brings the famous "Tip Of The Day" dialog to the
Swing toolkit.

------- The Distribution

In the distribution, there are several jar files, one per component
plus one including all components. This allows developers to include
only the classes for the components they want to use.

To run the demo from the distribution, use:

  java -jar lib\l2fprod-common-all.jar

Each jar embeds its own demo too. For example to view the JTaskPane
demo, use:

  java -jar lib\l2fprod-common-tasks.jar

------- Spring Rich Client

To use the Spring Rich Client integration, add
l2fprod-common-springrcp.jar to your classpath. Refer to the website
for information on the Spring Rich Client support within the library:
http://common.l2fprod.com/articles/taskpane-springrcp.php

------- Help, Questions

If you have any questions related to the L2FProd.com Common
Components, direct your emails to one of the following mailing lists
or check the archive or the FAQ:

mailto:users@l2fprod-common.dev.java.net Archive
https://l2fprod-common.dev.java.net/servlets/SummarizeList?listName=users

mailto:dev@l2fprod-common.dev.java.net
https://l2fprod-common.dev.java.net/servlets/SummarizeList?listName=dev

http://common.l2fprod.com/learn.php

If you have personal inquiries, you can reach me at "fred at
L2FProd.com". All technical questions must be directed to one of the
mailing lists.

------- Contributing

If you want to contribute to the project, see
http://common.L2FProd.com for directions.

The java source code is under src/java. Each component has its own
source directory plus the "shared" directory which is used by all
components. The goal is to be able to build separate jar files for
each component. Classes in "sandbox" are work-in-progress. Before
compiling, make sure to run the target "ant init.depend" to download
libraries used by the build.

The project requires at least JDK 1.4.2.

-fred at L2FProd.com

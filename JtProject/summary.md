## Issues in Setting up

* Invalid instructions
* Even more invalid instructions
* Extremely invalid instructions
* Invalid credentials
* setup SQL script is outdated, uses features that have been removed in latest
	MySQL

## Features added:

* Cart System
* Persistent Carts
* Product Search

## What developer could have done better / Lessons Learned

* Provide build/execute instructions that are IDE-independent (`mvn` directly)
* Have a set coding convention:
	* We found no set naming convention
* Follow the language's own coding conventions:
	* Java recommends a `CamelCase.java` file naming system, with `camel/case`
		(all lowercase) for package names. This was not followed.
* Define the constants away, rather than using literals throughout code:
	* database credentials were hardcoded in multiple places
* Use comments like `// TODO` to mark undone code, rather than simple comments
* Follow a max-line-width, having 318 characters long line is not sensible
* Do not unnecessarily use complex things just because they do exist:
	* Using SQL Transactions where a single query would do
* Either use a ORM, or don't. Don't do both:
	* the project uses SpringBoot's ORM to do database queries, but then reverts
		to using raw SQL in situations where the ORM is more than adequate.
* XSS exists, sanitize user input.
* Separation of Concerns is important, dont start executing raw SQL in frontend
	rendering templates.

## What could've been done better with more time?

* Rewritten the entire thing properly. Testing and fixing such a broken program
	is more effort than simply writing it from zero, given how simple it's
	functionality is.

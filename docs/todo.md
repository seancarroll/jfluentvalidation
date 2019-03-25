# TODOs

- [x] add Guard/Ensure class. Probably dont need to bring in guava for this and only a few String/Object helper methods
  - what about toString help classes...alternative?
- [ ] Various equals constraints
  - [ ] compareTo() == 0
  - [ ] ==
  - [ ] .equals
- [ ] Finish most of the main high level subjects
  - [ ] boolean
  - [ ] dates (Date / Calendar / LocalDate / etc)
  - [ ] collections (arrays, list, map, set)
- [ ] implement ability to compare other fields via lambda expressions
- [ ] implement ruleForEach
  - including filter (predicate) that should include/exclude items in the collection 
- [ ] implement soft constraints (when clause)
- [ ] implement including other validators
- [ ] localization
- [ ] Extensions - How would this work? Is there something else we can look at for inspiration? Review javax.enterprise.inject.spi Extension and hibernate validators ValidationExtension
  - [ ] joda
  - [ ] framework specific (spring, dropwizard, jersey, play, micronaut)
- [ ] add performance benchmark test and results
- [ ] Should I combine CharSequence length constraints into a single constraint? Similar to fluentvalidator?

Calendar
JapaneseDate
MinguoDate
ThaiBuddhistDate
HijrahDate

TODO: do we want Iterator as a first class constraint?

Where to put validation configuration such as clock provider?

- Update AbstractValidator to Default/Standard as I think I would like to support build a validator from that. 
  Include test and have sample in docs. Also show a way to create a custom validator 
- Handle method references (include test and have it in docs)

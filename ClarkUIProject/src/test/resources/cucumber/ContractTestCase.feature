Feature: Contract creation and validation

  @Smoke
  Scenario Outline: user create the contract and validate the same on manager page
    Given user is in contract page
    Then user select the "<Menu>" from contract page
   # Then user select the "<InsuranceType>" from request Page
   # And user select the "<Familienstatus>", "<Anstellung>", "<Rente>" and "<Selbstbeteiligung>"


    Examples:
      |Menu      |InsuranceType       |Familienstatus        |Anstellung|Rente|Selbstbeteiligung|
      |Angebote  |Privathaftpflicht   | Familie mit Kindern  |  Nein    | Ja  |    Nein         |
     # |Angebote  |Privathaftpflicht   | Singel               |  Nein    | Ja  |    Nein         |




  @Smoke
  Scenario Outline: user scenario 2
    Given user is in contract page
    Then user select the "<Menu>" from contract page

    Examples:
      |Menu      |InsuranceType       |Familienstatus        |Anstellung|Rente|Selbstbeteiligung|
      |Angebote  |Privathaftpflicht   | Familie mit Kindern  |  Nein    | Ja  |    Nein         |

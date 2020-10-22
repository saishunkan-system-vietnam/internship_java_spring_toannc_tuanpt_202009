<template>
  <v-card>
    <v-container>
      <v-icon large color="green darken-2"> mdi-soccer </v-icon>
      <b-row>
        <b-col cols="12" sm="2">
          <v-img height="100" width="100" :src="team.logo"> </v-img>
        </b-col>
        <b-col
          cols="12"
          sm="2"
          style="
            height: 100px;
            line-height: 100px;
            white-space: nowrap;
            font-size: 25px;
            color: #84705b;
          "
        >
          {{ team.nameTeam }}
        </b-col>
        <v-tabs
          v-model="tab"
          class="p-1"
          fixed-tabs
          background-color="#95afca"
          color="Black"
        >
          <v-tabs-slider></v-tabs-slider>
          <v-tab>Summary</v-tab>
          <v-tab>Results</v-tab>
          <v-tab>Fixtures</v-tab>
          <v-tab>Member</v-tab>
        </v-tabs>
        <v-tabs-items v-model="tab" style="margin-top: 50px">
          <v-tab-item style="min-height: 1000px">
            <v-container>
              <div>
                <b-card no-body>
                  <b-tabs
                    card
                    active-nav-item-class="font-weight-bold text-uppercase text-danger"
                  >
                    <b-tab title="Information" active>
                      <div>
                        <v-simple-table style="width: 1221px">
                          <tbody>
                            <tr>
                              <td>Total Member</td>
                              <td>
                                {{ !!team.profile ? team.profile.length : "" }}
                              </td>
                            </tr>
                            <tr>
                              <td>Description</td>
                              <td>{{ team.description }}</td>
                            </tr>
                            <tr>
                              <td>Total match</td>
                              <td>{{ team.totalmatch }}</td>
                            </tr>
                            <tr>
                              <td>Total Win</td>
                              <td>{{ team.totalwin }}</td>
                            </tr>
                          </tbody>
                        </v-simple-table>
                      </div>
                    </b-tab>
                  </b-tabs>
                </b-card>
              </div>
              <div style="padding-top: 50px">
                <b-card no-body>
                  <b-tabs
                    card
                    active-nav-item-class="font-weight-bold text-uppercase text-danger"
                  >
                    <div v-if="results == ''">There is no Lastest Scores</div>

                    <b-tab title="Lastest Scores" active>
                      <div>
                        <div v-for="(item, index1) in results" :key="index1">
                          <template v-if="index1 < 3">
                            <h5>{{ item.nameTour }}</h5>
                            <v-simple-table style="width: 1221px">
                              <tbody>
                                <tr
                                  v-for="(item, index2) in item.schedule"
                                  color="primary"
                                  :key="index2"
                                  v-b-popover.hover.top="'Click to see details'"
                                  @click="detail(item)"
                                >
                                  <template v-if="index2 < 5">
                                    <td>
                                      {{ item.timeEnd }}
                                    </td>
                                    <td>
                                      {{ !!item ? item.team[0].nameTeam : "" }}
                                    </td>
                                    <td>
                                      {{
                                        item.status == 2 ? item.scoreTeam1 : "?"
                                      }}
                                      -
                                      {{
                                        item.status == 2 ? item.scoreTeam2 : "?"
                                      }}
                                    </td>
                                    <td>
                                      {{ !!item ? item.team[1].nameTeam : "" }}
                                    </td>
                                  </template>
                                </tr>
                              </tbody>
                            </v-simple-table>
                          </template>
                          <div
                            class="text-center"
                            style="font-size: 15px"
                            v-if="index1 > 4"
                          >
                            <a href="#results" @click="tab = 1">
                              Show more matchs
                              <v-icon>mdi-arrow-down-thick</v-icon></a
                            >
                          </div>
                        </div>
                      </div>
                    </b-tab>
                  </b-tabs>
                </b-card>
              </div>
              <div style="padding-top: 50px">
                <b-card no-body>
                  <b-tabs
                    card
                    active-nav-item-class="font-weight-bold text-uppercase text-danger"
                  >
                    <b-tab title="Schedule" active>
                      <div v-if="fixtures == ''">There is no schedule</div>
                      <div v-for="(item2, index3) in fixtures" :key="index3">
                        <template v-if="index3 < 3">
                          <h5>{{ item2.nameTour }}</h5>
                          <v-simple-table style="width: 1221px">
                            <tbody>
                              <tr
                                v-for="(item2, index4) in item2.schedule"
                                color="primary"
                                :key="index4"
                                v-b-popover.hover.top="'Click to see details'"
                                @click="detail(item2)"
                              >
                                <template v-if="index4 < 5">
                                  <td>
                                    {{ item2.timeEnd }}
                                  </td>
                                  <td>
                                    {{ !!item2 ? item2.team[0].nameTeam : "" }}
                                  </td>
                                  <td>
                                    {{
                                      item2.status == 2 ? item2.scoreTeam1 : "?"
                                    }}
                                    -
                                    {{
                                      item2.status == 2 ? item2.scoreTeam2 : "?"
                                    }}
                                  </td>
                                  <td>
                                    {{ !!item2 ? item2.team[1].nameTeam : "" }}
                                  </td>
                                </template>
                              </tr>
                            </tbody>
                          </v-simple-table>
                        </template>
                      </div>
                    </b-tab>
                  </b-tabs>
                </b-card>
              </div>
            </v-container>
          </v-tab-item>
          <v-tab-item style="min-height: 1000px">
            <div v-if="results == ''" class="text-center">
              <div
                class="d-flex flex-column justify-space-between align-center"
              >
                <v-img
                  max-height="350"
                  max-width="350"
                  src="@/assets/hiclipart.com.png"
                ></v-img>
                No results
              </div>
            </div>
            <v-container>
              <div v-for="(item, index1) in results" :key="index1" id="results">
                <h5>{{ item.nameTour }}</h5>
                <v-simple-table style="width: 1221px">
                  <tbody>
                    <tr
                      v-for="(item, index2) in item.schedule"
                      color="primary"
                      :key="index2"
                      v-b-popover.hover.top="'Click to see details'"
                      @click="detail(item)"
                    >
                      <td style="width: 250px">
                        {{ item.timeEnd }}
                      </td>
                      <td style="width: 150px">
                        {{ !!item ? item.team[0].nameTeam : "" }}
                      </td>
                      <td style="width: 150px">
                        {{ item.status == 2 ? item.scoreTeam1 : "?" }}
                        -
                        {{ item.status == 2 ? item.scoreTeam2 : "?" }}
                      </td>
                      <td>{{ !!item ? item.team[1].nameTeam : "" }}</td>
                    </tr>
                  </tbody>
                </v-simple-table>
              </div>
            </v-container>
          </v-tab-item>
          <v-tab-item style="min-height: 1000px">
            <v-container>
              <div v-if="fixtures == ''" class="text-center">
                <div
                  class="d-flex flex-column justify-space-between align-center"
                >
                
                  <v-img
                    max-height="350"
                    max-width="350"
                    src="@/assets/hiclipart.com.png"
                  ></v-img>
                  No match is fixtures
                </div>
              </div>
              <div v-for="(item2, index3) in fixtures" :key="index3">
                <template v-if="index3 < 3">
                  <h5>{{ item2.nameTour }}</h5>
                  <v-simple-table style="width: 1221px">
                    <tbody>
                      <tr
                        v-for="(item2, index4) in item2.schedule"
                        color="primary"
                        :key="index4"
                        v-b-popover.hover.top="'Click to see details'"
                        @click="detail(item2)"
                      >
                        <template v-if="index4 < 5">
                          <td>
                            {{ item2.timeEnd }}
                          </td>
                          <td>
                            {{ !!item2 ? item2.team[0].nameTeam : "" }}
                          </td>
                          <td>
                            {{ item2.status == 2 ? item2.scoreTeam1 : "?" }}
                            -
                            {{ item2.status == 2 ? item2.scoreTeam2 : "?" }}
                          </td>
                          <td>{{ !!item2 ? item2.team[1].nameTeam : "" }}</td>
                        </template>
                      </tr>
                    </tbody>
                  </v-simple-table>
                </template>
              </div>
            </v-container>
          </v-tab-item>
          <v-tab-item style="min-height: 1000px">
            <v-container>
              <v-simple-table style="width: 1221px">
                <thead>
                  <th class="text-center">Avatar</th>
                  <th class="text-center">Name</th>
                  <th class="text-center">Nation</th>
                  <th class="text-center">Gender</th>
                  <th class="text-center">Age</th>
                </thead>
                <tbody>
                  <tr
                    v-for="(item, index) in team.profile"
                    color="primary"
                    :key="index"
                    v-b-popover.hover.top="'Click to see details'"
                    @click="detailMember(item)"
                  >
                    <td class="text-center">
                      <v-avatar class="profile" color="grey" size="70" tile>
                        <v-img :src="item.avatar"></v-img>
                      </v-avatar>
                    </td>
                    <td class="text-center">{{ item.name }}</td>
                    <td class="text-center">{{ item.address }}</td>
                    <td class="text-center">{{ item.gender }}</td>
                    <td class="text-center">{{ item.age }}</td>
                  </tr>
                </tbody>
              </v-simple-table>
            </v-container>
          </v-tab-item>
        </v-tabs-items>
      </b-row>
    </v-container>
  </v-card>
</template>
<script>
export default {
  data() {
    return {
      tab: null,
      team: "",
      results: "",
      fixtures: "",
      detailTeam: "",
    };
  },
  created() {
    this.getDataTeam();
    this.getTourByTeam();
    this.getFixtures();
  },
  methods: {
    getDataTeam() {
      this.$store
        .dispatch("team/getById", this.$route.params.id)
        .then((res) => {
          this.team = res.data;
          console.log(this.team);
        });
    },
    getTourByTeam() {
      this.$store
        .dispatch("tournament/getTourByTeam", this.$route.params.id)
        .then((res) => {
          this.results = res.data;
        });
    },
    getFixtures() {
      this.$store
        .dispatch("tournament/getFixtures", this.$route.params.id)
        .then((res) => {
          this.fixtures = res.data;
        });
    },
    detail(data) {
      var myWindow = window.open(
        "http://localhost:8080/detail/" + data.idSchedule,
        "myWindow",
        "width=600px,height=600"
      );
    },
    detailMember(item) {
      this.$router.push({ path: "/PlayerProfile/" + item.id });
    },
  },
  watch: {
    tab() {},
  },
};
</script>
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
          <v-tab-item>
            <v-container>
              <h4>Lastest Scores</h4>
              <div>
                <div v-for="(item, index1) in results" :key="index1">
                  <template v-if="index1 < 3">
                    {{ item.nameTour }}
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
                              {{ item.status == 2 ? item.scoreTeam1 : "?" }}
                              -
                              {{ item.status == 2 ? item.scoreTeam2 : "?" }}
                            </td>
                            <td>{{ !!item ? item.team[1].nameTeam : "" }}</td>
                          </template>
                        </tr>
                      </tbody>
                    </v-simple-table>
                  </template>
                </div>

                <div class="text-center" style="font-size: 15px" v-if="index1<4">
                  <a href="#results" @click="tab = 1">
                    Show more matchs <v-icon>mdi-arrow-down-thick</v-icon></a
                  >
                </div>
              </div>
              <div>
                <h4>Schedule</h4>
                <div v-for="(item2, index3) in fixtures" :key="index3">
                  <template v-if="index3 < 3">
                    {{ item2.nameTour }}
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
              </div>
            </v-container>
          </v-tab-item>
          <v-tab-item>
            <v-container>
              <div v-for="(item, index1) in results" :key="index1" id="results">
                {{ item.nameTour }}
                <v-simple-table style="width: 1221px">
                  <tbody>
                    <tr
                      v-for="(item, index2) in item.schedule"
                      color="primary"
                      :key="index2"
                      v-b-popover.hover.top="'Click to see details'"
                      @click="detail(item)"
                    >
                      <td>
                        {{ item.timeEnd }}
                      </td>
                      <td>
                        {{ !!item ? item.team[0].nameTeam : "" }}
                      </td>
                      <td>
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
          <v-tab-item
            ><div v-for="(item2, index3) in fixtures" :key="index3">
              <template v-if="index3 < 3">
                {{ item2.nameTour }}
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
  },
  watch: {
    tab() {},
  },
};
</script>